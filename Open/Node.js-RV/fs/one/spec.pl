:- module(spec,[trace_expression/2, match/3, match/2]).

:- use_module(node(func_match)).

match(fs_one, Json, open)  :- func_pre(Json, 'fs.open',  _, _, _).
match(fs_one, Json, cb)    :- cb_pre(  Json, _,          _, _, _).
match(fs_one, Json, write) :- func_pre(Json, 'fs.write', _, _, _).
match(fs_one, Json, close) :- func_pre(Json, 'fs.close', _, _, _).

% only consider callbacks and fs operations
match(fs_one, Json, myfilter) :- cb_pre(Json, _, _, _, _).
match(fs_one, Json, myfilter) :- func_pre(Json, NameString, _, _, _),
                                 string_chars(NameString, ['f','s','.'|_]).

match(E, ET) :- match(_, E, ET).

trace_expression(fs_one, T) :- T = myfilter >> (epsilon \/ (open:cb:O)),
                               O = (write:cb:O) \/ (close:cb:epsilon),
	                           numbervars(T, 0, _).
