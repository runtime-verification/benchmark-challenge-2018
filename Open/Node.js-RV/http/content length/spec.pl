:- module(spec,[trace_expression/2, match/2]).

% event domain
:- file_search_path(node, X), writeln(user_output, X).
:- use_module(node(func_match)).

% top-level filter
match( Json, filter) :- func_post(Json, 'http.request', _, _, _).
match( Json, filter) :- func_pre(Json, 'write', _, _, _).
match( Json, filter) :- func_pre(Json, 'end', _, _, _).

% event types
% ID: unique HTTP request identifier
% CL: content-length

match( Json, chunk_req(ID)) :-
	func_post(Json, 'http.request', Args, _, ID),
	Args = [Opts|_],
	(Opts.get(headers)=Hs ->
		not(Hs.get('content-length')=_);
		true).

match( Json, req(ID, CL)) :-
	func_post(Json, 'http.request', Args, _, ID),
	Args = [Opts|_],
	%TODO case-insensitive check
	Opts.get(headers).get('content-length')=CL.

match( Json, write(ID)) :- func_pre(Json, 'write', _, _, ID).
match( Json, end(ID))   :- func_pre(Json, 'end',   _, _, ID).

%TODO see what happens if data in JS is a Buffer instance
match( Json, write(ID, CL, CL2)) :-
	func_pre(Json, 'write', _, Args, ID),
	Args = [Data|_],
	atom_length(Data, L),
	CL2 is CL-L.

%TODO see what happens if data in JS is a Buffer instance
match( Json, end(ID, L)) :-
	func_pre(Json, 'end', _, Args, ID),
	Args = [Data|_],
	atom_length(Data, L).

% trace expression
    trace_expression(http_content_length, Main) :-
        Main = filter >> T,
	T = eps \/ Chunked \/ Unchunked,
	Chunked = var(id, chunk_req(var(id)):(Writes|T)),
	Writes = (write(var(id)):Writes) \/ End,
	End = end(var(id)):eps, 
	Unchunked = var(id, var(cl, req(var(id), var(cl)):(W|T))),
	W = var(cl2, write(var(id), var(cl),  var(cl2)):W2) \/ E,
	W2 = var(cl,  write(var(id), var(cl2), var(cl) ):W ) \/ E2,
	E = end(var(id), var(cl)) :eps,
	E2 = end(var(id), var(cl2)):eps.
