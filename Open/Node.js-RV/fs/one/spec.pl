:- module(spec,[trace_expression/2,match/2]).

:- use_module(node(func_match)).

match(Json, open)  :- func_pre(Json, 'fs.open',  _, _, _).
match(Json, cb)    :- cb_pre(  Json, _,          _, _, _).
match(Json, write) :- func_pre(Json, 'fs.write', _, _, _).
match(Json, close) :- func_pre(Json, 'fs.close', _, _, _).

% only consider callbacks and fs operations
match(Json, myfilter) :- cb_pre(Json, _, _, _, _).
match(Json, myfilter) :- func_pre(Json, NameString, _, _, _),
                                 string_chars(NameString, ['f','s','.'|_]).

trace_expression(fs_one, T) :- T = myfilter >> (eps \/ (open:cb:O)),
                               O = (write:cb:O) \/ (close:cb:eps),
	                           numbervars(T, 0, _).
