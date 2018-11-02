:- module(spec,[trace_expression/2, match/2]).

:- use_module(node(func_match)).

match(Json, open(ID)) :- func_pre(Json, 'fs.open', ID, _, _).
match(Json, opencb(ID, FD)) :- cb_pre(Json, _, ID, [_,FD|_], _).

match(Json, write(ID, FD)) :- func_pre(Json, 'fs.write', ID, [FD|_], _).
match(Json, close(ID, FD)) :- func_pre(Json, 'fs.close', ID, [FD|_], _).
match(Json, cb(ID)) :- cb_pre(Json, _, ID, _, _).

% only consider callbacks and fs operations
match(Json, myfilter) :- cb_pre(Json, _, _, _, _).
match(Json, myfilter) :- func_pre(Json, NameString, _, _, _),
                         string_chars(NameString, ['f','s','.'|_]).

trace_expression(fs_many, T) :-
	T  = myfilter >> (eps \/ var(ID, open(ID):(Tf|T))),
	Tf = var(FD, opencb(ID, FD):W),
	W  = var(ID2, write(ID2, FD):cb(ID2):W) \/ var(ID3, close(ID3, FD):cb(ID3):eps),
	numbervars(T, 0, _).
