:- module(spec,[trace_expression/2, match/2]).

%:- discontiguous match/3.

:- use_module(node(func_match)).

match(Json, filter) :- func_pre_names(Json , ['http.createServer', 'write' , 'writeHead' , 'end']).
match(Json, filter) :- cb_pre(Json).

match(Json, createServer(CbId)) :- func_pre(Json , 'http.createServer', CbId, _ , _).

match(Json, write(RespId)) :- func_pre(Json , 'write' , _ , _ , RespId).
match(Json, write(RespId)) :- func_pre(Json , 'writeHead', _ , _ , RespId). %% writeHead allowed to be called multiple times and after write

match(Json, end(RespId)) :- func_pre(Json , 'end', _, _, RespId).

match(Json, cb_start(CbId,RespId)) :- cb_pre(Json , _ , CbId, _Args, [ _ , RespId | _ ] , _).

trace_expression('response_end', Main) :-
Main = filter >> var(cbId , createServer(var(cbId)):Start),
Start = var(respId , cb_start(var(cbId),var(respId)):(Writes|(eps\/Start))),
Writes = (write(var(respId)):Writes) \/ (end(var(respId)):eps).
