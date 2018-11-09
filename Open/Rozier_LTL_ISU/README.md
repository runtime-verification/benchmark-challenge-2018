# BenchmarkGenerator
This tool is designed to generate online runtime monitoring benchmarks from given formulas in Mission-Time Linear Temporal Logic (MLTL). The Globally (G), Eventually (F), and Until (U) operators are each support directly, while Next State (X) is supported implicitly as G[1,1] (i.e., "Globally in one time step"). Negation (-), conjunction (&), and disjunction (|) are also each supported. It should be noted that implication (->) is not directly supported and should instead be encoded using the identity "a -> b = -a | b". 

Time bounds are required for all temporal operators, with the macro "end" signifying that the upper bound on the operator should ensure it extends until the end of the generated trace (which is computed under the hood). The macro "INITIAL" can be used to encode specific start states, and to trigger effects at certain points during the trace. For example "INITIAL -> -a & -b & -c" (encoded without implication, in actuality), would ensure that the initial reported state in the benchmark has "-a & -b & -c". Similarly "INITIAL -> G[10,10] a" will ensure that "a" (which can be a more complex property) holds at time step 10. 

Upon tool execution, a csv consisting of a trace, with oracle included, will be generated, as well as the SMTLIB query used for this generation, and a log file containing both of the above artifacts and additional information.

For the sake of online benchmark generation, a MAXSAT/SMT approach is adopted. That is, a trace is generated which maximally satisfies the given set of formulas across all time steps. The sole exception to this rule is *the first time step*. At this time step, the given constraints are hard, and therefore all generated traces will have the oracle as true at this time. This is necessary to allow for the initial state manipulations and fault injections mentioned in the paragraph above. **The inability to generate a trace for a given formula is an indicator that the formula is not satisfiable.**

## Examples of Syntactically-Correct Formulas

G[0,3](-a | b | c)

(F[3,8](G[0,10] (a) | x) | somethingelse)

a U[0,10] b

## Running the tool

**Z3 must be installed to generate benchmarks, with the Z3 environmental variable correctly set (i.e., executing "z3" on the command line for a correctly configured system should run the solver).** The formula of interest must be written in a separate file, to be passed to the tool for generation. To run the tool, using the jar included in this repo, execute the following command:

```java -jar BenchmarkGenerator.jar [INPUT FILENAME] [TRACE LENGTH] {-o [OUTPUT FOLDER NAME]}```

A directory called "OUTPUT" will be created, with subdirectories for each run of the tool, named as given in the above command.