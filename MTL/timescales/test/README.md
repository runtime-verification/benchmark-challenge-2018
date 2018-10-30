This directory contains source files to run `smallsuite` benchmarks using the monitoring tool `reelay` and a Makefile to compile and run benchmarks. First we need to install `reelay`. The easiest way to install `reelay` is to run the following command (assuming `git` installed). 

    pip3 install git+https://github.com/doganulus/reelay.git

Compiling generated code further requires `Boost C++` libraries installed on your system. If not, please run the command below if you are Debian/Ubuntu.

    sudo apt-get install libboost-all-dev

Then run a particular benchmark from the smallsuite such that

    make test_absent_aq
		 test_absent_br
		 test_absent_bqr
		 test_always_aq
		 test_always_br
		 test_always_bqr
		 test_recur_glb
		 test_recur_bqr
		 test_respond_glb
		 test_respond_bqr
		 test_challenge_pandq
		 test_challenge_delay

These commands will run `reelay` for the specification file in the benchmark and generate a C++ class that monitors the property. This class are the compiled together with the corresponding (main) application file in `src` directory to generate a monitoring program. Finally we run the program over the trace file in the benchmark.
