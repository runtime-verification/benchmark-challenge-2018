:- use_module(library(http/json)).
:- use_module(library(http/json_convert)).

:- use_module(trace_expressions_semantics).

%% arguments
%% first argument: the filename containing the specified trace expression
%% second argument: a log file containing the trace

read_trace(Trace) :-
    open(Trace, read, Str),
    read_file(Str,Lines),
    close(Str),
    trace_expression(_, TE),
    trace(Lines, TE, 1), nl.

read_file(Stream,[]) :-
    at_end_of_stream(Stream).

read_file(Stream,[X|L]) :-
    \+ at_end_of_stream(Stream),
    read_line_to_codes(Stream,Codes),
    atom_chars(X, Codes),
    read_file(Stream,L), !.

trace([], TE, _) :- may_halt(TE) -> write('Execution terminated correctly');write('Execution aborted').
trace([E|Es], TE, N) :-	next(TE, E, TE2) ->
	(write('matched '), write(N), nl, N2 is N+1, trace(Es, TE2, N2));
	(write('ERROR on event '), write(E)).

% load spec
:- current_prolog_flag(argv, [_, Spec]), use_module(Spec).

% read trace
main :- current_prolog_flag(argv, [Trace, _]), read_trace(Trace), halt.
main :- halt(1).

:- set_prolog_flag(verbose, silent).
:- initialization(main).
