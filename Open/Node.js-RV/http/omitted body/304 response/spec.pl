:- module(spec,[trace_expression/2, match/2]).

:- use_module(node(func_match)).

match(Json, filter) :- func_pre(Json , 'http.createServer' , _ , _ , _).

match(Json, filter) :- func_pre(Json , 'write' , _ , _ , _).

match(Json, filter) :- func_pre(Json , 'writeHead' , _ , _ , _).

match(Json, filter) :- func_pre(Json , 'end' , _ , _ , _).

match(Json, createServer(Id)) :- func_pre(Json , 'http.createServer' , Id , _ , _).

match(Json, write) :- func_pre(Json , 'write' , _ , _ , _).

match(Json, end) :- func_pre(Json , 'end' , _ , _ , _).

match(Json, writeHead304) :- func_pre(Json , 'writeHead' , _ , [304|_] , _).

match(Json, writeHeadNo304) :- func_pre(Json , 'writeHead' , _ , [Code|_] , _) , Code \= 304.

match(Json, endNoBody) :- func_pre(Json , 'end' , _ , [] , _).

match(Json, endNoBody) :- func_pre(Json , 'end' , _ , [MaybeChunk|_] , _) , not(string_chars(MaybeChunk , _)).


trace_expression('response304', Main) :-
Main = (filter >> var( id, (createServer(var(id)) :Start))),
Start = eps \/ ((((write : Written) \/ (end : Start)) \/ (writeHead304 : Head304)) \/ (writeHeadNo304 : Written)),
Written = (((write : Written) \/ (end : Start)) \/ (writeHeadNo304 : Written)),
Head304 = (endNoBody : Start).
