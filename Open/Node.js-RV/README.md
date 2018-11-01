In order to run use this benchmark, the following software needs to be installed:
- Node.js (to run programs)
- npm (Node.js package manager, to automatically install libraries)
- SWI-Prolog (to run the oracle)

Installation information can be found in the relevant websites, and the software is available on all platforms.

After installing the relevant software, move the directory and install all the libraries:

    $ npm i

Then, tu run the example and to produce a trace file:

    $ ./run-instrumented.sh fs/many/prog/wrong.js trace.txt

To verify the trace against the specification with the oracle:

    $ ./run-oracle.sh trace.txt fs/many/spec.pl

Note: we are currently preparing all the other examples so that the process can be as automated and easy to use as possible.