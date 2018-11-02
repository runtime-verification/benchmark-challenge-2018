:- module(spec,[trace_expression/2, match/2]).

:- use_module(node(func_match)).

match(Json, filter) :- func_pre_names(Json , ['end' , 'writeHead' , 'http.createServer']).

match(Json, createServer) :- func_pre_name(Json , 'http.createServer').

match(Json, end(ResponseId)) :- func_pre(Json , 'end' , _ , _ , ResponseId).

match(Json, writeHead(ResponseId)) :- func_pre(Json , 'writeHead' , _ , _ , ResponseId).

match(Json, notId(ForbiddenId)) :- func_pre(Json , _ , _ , _ , ResponseId) , ResponseId \= ForbiddenId.

match(Json, filterId(ResponseId)) :- func_pre(Json , _ , _ , _ , ResponseId).


trace_expression('head_after_end', Main) :-
Main = (filter >> ((createServer : Start))),
Start = eps \/ var( responseId, ((HeadThenEnd \/ OnlyEnd))),
HeadThenEnd = (writeHead(var(responseId)) :((((end(var(responseId)) :eps)) | Another))),
OnlyEnd = (end(var(responseId)) :Another),
Another = (((notId(var(responseId)) >> Start)) /\ ((filterId(var(responseId)) >> eps))).
