:- module(spec,[trace_expression/2, match/2]).

:- use_module(node(func_match)).

match(Json, filter) :- func_pre_name(Json , 'end').

match(Json, filter) :- func_post_name(Json , 'http.request').

match(Json, request(ReqId)) :- func_post(Json , 'http.request' , [Options|_] , _ , ReqId) , Options.get(method)=='HEAD'.

match(Json, endWithoutData(ReqId)) :- func_pre(Json , 'end' , _ , [] , ReqId).

match(Json, endWithoutData(ReqId)) :- func_pre(Json , 'end' , _ , [null] , ReqId).

%%match(Json, endWithoutData(ReqId)) :- func_pre(Json , 'end' , _ , [MaybeChunk|_] , ReqId) , not(string_chars(MaybeChunk , _)).

trace_expression('head_request', Main) :-
Main = filter>>T, T = eps \/ var( reqId, (request(var(reqId)) :((((endWithoutData(var(reqId)) :eps)) | T)))).
