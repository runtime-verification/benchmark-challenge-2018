:- module(trace_expressions,[next/3,may_halt/1]).

:- use_module(library(coinduction)).
:- coinductive apply_sub_trace_exp/3.

:- use_module(library(http/json)).  %% only needed to patch the json dicts bug

/*******************************************************************************************/
/*                              PARAMETRIC TRACE EXPRESSIONS                                                   */
/*    Aug 11, 2017: fixed bug with coinduction                                                                   */
/*    March 2, 2018: test with generic trace expressions                                                    */
/*******************************************************************************************/

/* Transition rules */

% (main)
% next(T, E, T1) :- next(T, E, T1, []).

%% old patch to avoid problems with json dicts, resolved by using value_string_as(atom) option
next(T, String, T1) :- open_string(String,Stream),json_read_dict(Stream,E,[value_string_as(atom)]), next(T, E, T1, []).

% next transition function (parametric version)
next(ET:T, E, T, S) :- match(E, ET, S).

next(T1\/_, E, T2, S) :- next(T1, E, T2, S),!.
next(_\/T1, E, T2, S) :- !,next(T1, E, T2, S).

next(T1|T2, E, T, S) :- next(T1, E, T3, S),!,fork(T3, T2, T).
next(T1|T2, E, T, S) :- !, next(T2, E, T3, S),fork(T1, T3, T).

next(T1*T2, E, T, S) :- next(T1, E, T3, S),!,concat(T3, T2, T).
next(T1*T2, E, T3, S) :- !,may_halt(T1),next(T2, E, T3, S).

next(T1/\T2, E, T, S) :- !,next(T1, E, T3, S1),next(T2, E, T4, S2),merge(S1, S2, S),conj(T3, T4, T).

%% version with split (really optimized?) %% important, the cut after apply_sub_trace_exp is essential to avoid divergence in case of failure due to coindcution
next(var(X, T), E, T3, RetSubs) :- !,next(T, E, T1, Subs1),split(X,Subs1,XSubs,RetSubs),apply_sub_trace_exp(XSubs,T1,T2),!,(XSubs==[]->T3=var(X,T2);T3=T2).

next(ET>>T, E, ET>>T1, S) :- !,match(E, ET, S1) -> next(T, E, T1, S2),merge(S1, S2, S);T1=T,S=[].

next('?'(ET,T1,T2), E, '?'(ET,T3,T4), S) :- !,match(E,ET,S1) -> next(T1,E,T3,S2),T4=T2,merge(S1, S2, S);next(T2,E,T4,S),T3=T1.   

%% proposal for generics

next(app(gen(X,T1),Arg), E, T3, S) :- !,apply_sub_trace_exp([X=Arg], T1, T2),next(T2, E, T3, S).    
    

%% proposal for guarded trace expressions

next(guarded(P,T1,T2),E,T,S) :- !,solve(P,S1) -> next(T1,E,T,S2), merge(S1, S2, S);next(T2,E,T,S).

%% proposal for if-then-else
next(ifelse(ET, T1, T2), E, T, S) :- !,
	(match(E, ET, S1) ->
		(S=S1, T=T1) ;
		next(T2, E, T, S)).

%% match predicate
    
%% match(E,ET,S) :- copy_term(ET,FreshET),match(E,FreshET),unifiable(ET,FreshET,S). %% old version
match(E,ET,Subs) :- copy_term_with_vars(ET,[],FreshET,Subs), match(E,FreshET). %%,write('matching '),write(E),write(' with '),writeln(FreshET),match(E,FreshET),write('matched ').
%% debugging version
%% match(E,ET,Subs) :- copy_term_with_vars(ET,FreshET,Subs),write('matching '),write(E),write(' with '),write(FreshET),match(E,FreshET),writeln('matched ').

%% solve predicate
%% solve(P,Subs) :- !,copy_term_with_vars(P,[],FreshP,Subs),FreshP.
solve(P,Subs) :- !,copy_term_with_vars(P,FreshP,Subs),FreshP.

may_halt(eps) :- !.
may_halt(T1\/T2) :- (may_halt(T1), !; may_halt(T2)).
may_halt(T1|T2) :- !, may_halt(T1), may_halt(T2).
may_halt(T1*T2) :- !, may_halt(T1), may_halt(T2).
may_halt(T1/\T2) :- !, may_halt(T1), may_halt(T2).
may_halt(var(_, T)) :- !, may_halt(T).
may_halt(_>>T) :- !, may_halt(T).
may_halt(?(_,T1,T2)) :- !, may_halt(T1), may_halt(T2).

%% proposal for generics

may_halt(app(gen(X,T1),Arg)) :- !,apply_sub_trace_exp([X=Arg],T1,T2),may_halt(T2).    

%% proposal for guarded trace expressions

may_halt(guarded(P,T1,T2)) :- !,solve(P,_)->may_halt(T1);may_halt(T2).

%% proposal for if-then-else
% may_halt never holds?
    
%%% optimizations
fork(eps, T, T) :- !.
fork(T, eps, T) :- !.
fork((T1l|T1r), T2, (T1l|(T1r|T2))) :- !.
fork(T1, T2, (T1|T2)).

concat(eps, T, T) :- !.
concat(T, eps, T) :- !.
concat((T1l*T1r), T2, T1l*(T1r*T2)) :- !.
concat(T1, T2, T1*T2).

conj(eps/\eps, eps) :- !.
conj((T1l/\T1r), T2, T1l/\(T1r/\T2)) :- !.
conj(T1, T2, T1/\T2).

%%% substitutions

% split into a singleton/empty substitution and the rest
split(_,[],[],[]).
split(X,[Y=V|S],[Y=V],S) :- X==Y,!.
split(X,[A|S],S1,[A|S2]) :- split(X,S,S1,S2).

% substitution application (only singleton or empty substitutions) 
apply_sub_trace_exp([],T,T).  %% optimization
apply_sub_trace_exp(_,eps,eps).
apply_sub_trace_exp(S, ET1:T1, ET2:T2) :- !,apply_sub_event_type(S,ET1,ET2),apply_sub_trace_exp(S,T1,T2).
apply_sub_trace_exp(S,T1\/T2,T3\/T4) :- !,apply_sub_trace_exp(S,T1,T3),apply_sub_trace_exp(S,T2,T4).
apply_sub_trace_exp(S,T1|T2,T3|T4) :- !,apply_sub_trace_exp(S,T1,T3),apply_sub_trace_exp(S,T2,T4).
apply_sub_trace_exp(S,T1*T2,T3*T4) :- !,apply_sub_trace_exp(S,T1,T3),apply_sub_trace_exp(S,T2,T4).
apply_sub_trace_exp(S,T1/\T2,T3/\T4) :- !,apply_sub_trace_exp(S,T1,T3),apply_sub_trace_exp(S,T2,T4).
apply_sub_trace_exp([Y=V],var(X, T1),var(X, T2)) :- Y==X -> T2=T1;apply_sub_trace_exp([Y=V],T1,T2).
apply_sub_trace_exp(S,ET1>>T1,ET2>>T2) :- !,apply_sub_event_type(S,ET1,ET2),apply_sub_trace_exp(S,T1,T2).
apply_sub_trace_exp(S,'?'(ET1,T1,T2),'?'(ET2,T3,T4)) :- !,apply_sub_event_type(S,ET1,ET2),apply_sub_trace_exp(S,T1,T3),apply_sub_trace_exp(S,T2,T4).

%% proposal for generics

apply_sub_trace_exp([Y=V],app(gen(X,T1),Arg1),app(gen(X,T2),Arg2)) :- !,apply_sub_arg([Y=V],Arg1,Arg2),(Y==X -> T2=T1;apply_sub_trace_exp([Y=V],T1,T2)).

%% proposal for guarded trace expressions

apply_sub_trace_exp(S,guarded(P1,T1,T2),guarded(P2,T3,T4)) :- !,apply_sub_pred(S,P1,P2),apply_sub_trace_exp(S,T1,T3),apply_sub_trace_exp(S,T2,T4).

%% proposal for if-then-else
apply_sub_trace_exp(S, ifelse(ET, T1, T2), ifelse(ETs, T1s, T2s)) :-
	!,
	apply_sub_event_type(S, ET, ETs),
	apply_sub_trace_exp(S, T1, T1s),
	apply_sub_trace_exp(S, T2, T2s).

% substitution inside event types
apply_sub_event_type([],ET,ET) :- !.
apply_sub_event_type([X=V],var(Y),ET) :- !,(Y==X -> ET=V;ET=var(Y)).
apply_sub_event_type(S,ET1,ET2) :- ET1=..[F|Args1],apply_sub_event_type_list(S,Args1,Args2),ET2=..[F|Args2].

apply_sub_event_type_list(_,[],[]).
apply_sub_event_type_list(S,[ET1|ETL1],[ET2|ETL2]) :- apply_sub_event_type(S,ET1,ET2),apply_sub_event_type_list(S,ETL1,ETL2).

%% proposal for generics
%% substitution inside generic arguments

apply_sub_arg(S,Arg1,Arg2) :- !,apply_sub_event_type(S,Arg1,Arg2).

%% proposal for guarded trace expressions
%% substitution inside guard predicates

apply_sub_pred(S,P1,P2) :- !,apply_sub_event_type(S,P1,P2).

% add: auxiliary predicate for merge
% add a new association to the substitution, no addition is performed if the association is already in the substitution,
% the predicate fails if the association is not compatible with the substitution
add(A, [], [A]).
add(X=V1, [Y=V2|S], [X=V1|S]) :- X==Y,!,V1==V2.
add(A1, [A2|S], [A2|S1]) :- add(A1, S, S1).

% merge two substitutions; fails if they do not agree on the shared variables 
merge([], L, L).
merge([H|T], L, L2) :-
  add(H, L, L1),
  merge(T, L1, L2), !.

% lookup: auxiliary predicate for copy_term_with_vars
% lookup(X,Subs,V,Subs') looks up variable name X in Subs
% if found returns the corresponding associated Prolog variable V and Subs'=Subs
% otherwise returns a fresh Prolog variable V, Subs' is obtained from Subs by adding the association X=V 

lookup(X,[],Y,[X=Y]).
lookup(X,[X=V|Subs],V,[X=V|Subs]) :- !.
lookup(X,[Y=V1|Subs1],V2,[Y=V1|Subs2]) :- lookup(X,Subs1,V2,Subs2).

% copy_term_with_vars
% needed to properly manage coinduction in the presence of binder variables (they cannot be ordinary Prolog variables!)
% variables in event types are represented with the syntax "var(name)" 
% Subs is the returned list mapping variable names into corresponding Prolog variables

copy_term_with_vars(ET,FreshET,Subs) :- copy_term_with_vars(ET,[],FreshET,Subs).

copy_term_with_vars(var(X),Subs1,Var,Subs2) :- lookup(X,Subs1,Var,Subs2),!.
copy_term_with_vars(ET1,Subs1,ET2,Subs2) :- ET1=..[F|Args1],copy_term_with_vars_list(Args1,Subs1,Args2,Subs2),ET2=..[F|Args2].

copy_term_with_vars_list([],Subs,[],Subs).
copy_term_with_vars_list([ET1|ETL1],Subs1,[ET2|ETL2],Subs3) :- copy_term_with_vars(ET1,Subs1,ET2,Subs2),copy_term_with_vars_list(ETL1,Subs2,ETL2,Subs3).

