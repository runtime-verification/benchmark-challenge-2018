:- module(spec,[trace_expression/2, match/2]).

:- use_module(node(func_match)).

match(Json, filter) :- func_pre(Json , 'on' , _ , ['data'|_] , _).

match(Json, filter) :- func_pre(Json , 'on' , _ , ['end'|_] , _).

match(Json, onData(ResponseId)) :- func_pre(Json , 'on' , _ , ['data'|_] , ResponseId).

match(Json, onEnd(ResponseId)) :- func_pre(Json , 'on' , _ , ['end'|_] , ResponseId).

trace_expression('unconsumed', filter >> Main) :-
Main = var( responseId, (onData(var(responseId)) :((((onEnd(var(responseId)) :eps)) | Main)))).
