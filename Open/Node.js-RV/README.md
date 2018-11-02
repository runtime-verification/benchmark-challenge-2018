# Installation
In order to run use this benchmark, the following software needs to be installed:
- [Node.js](https://nodejs.org/en/) (to run programs)
- [npm](https://www.npmjs.com/) (Node.js package manager, to automatically install libraries)
- [SWI-Prolog](http://www.swi-prolog.org/) (to run the oracle)

Installation information can be found in the relevant websites, and the software is available on all platforms.

After installing software above, move to this directory and install all the libraries:

    $ npm i

# Usage
## Benchmark Suites Structure
The two benchmark suites can be found in directories `fs` and `http`.
Inside them, every example has its own directory with two variants, `correct` and `wrong`: the former contains the version of the program to be monitored that is correct, the latter includes the buggy version that does not satisfy the specification.
The specification itself is written down in every example folder in a file called `spec.pl`.

Further more, every example comes with a shell script that instruments the program and executes it appropriately, called `run-instrumented.sh`.

## Producing the Trace File
To run the example and to produce a trace file, execute the shell script in the example folder.
For instance, to run the `fs/many` example showing the bug, saving the trace to a file called `trace.txt`, type:

    $ ./fs/many/wrong/run-instrumented.sh trace.txt

The produced trace file contains a JSON object for each line: these encode the observed events.

## Verifying the Trace
To verify the trace against the specification with the Prolog oracle:

    $ ./run-oracle.sh trace.txt fs/many/spec.pl
