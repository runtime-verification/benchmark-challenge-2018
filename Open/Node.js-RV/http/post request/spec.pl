:- module(spec,[trace_expression/2, match/2]).

:- use_module(node(func_match)).

match(Json, filter) :- func_pre_name(Json , 'end').

match(Json, filter) :- func_post_name(Json , 'http.request').

match(Json, filter) :- cb_pre(Json , _, _, _, _).
     
match(Json, request(ReqId)) :- func_post(Json , 'http.request' , _ , _ , ReqId).

match(Json, end(ReqId,CbId)) :- func_pre(Json , 'end' , CbId , _  , ReqId).

match(Json, end_cb(CbId)) :- cb_pre(Json, _ , CbId, _ , _).
    
trace_expression('head_request', filter >> T) :-
    
    T = var( reqId, request(var(reqId)) : (var( cbId, end(var(reqId),var(cbId)) : (end_cb(var(cbId)) : (eps \/ T))))). 
