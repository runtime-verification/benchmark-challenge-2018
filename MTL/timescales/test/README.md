This directory contains source files to run `smallsuite` benchmarks using the monitoring tool [Reelay](https://github.com/doganulus/reelay) and a Makefile to compile and run benchmarks. The easiest way to install `reelay` is to run the following command (assuming `git` installed):

    pip3 install git+https://github.com/doganulus/reelay.git

Compiling generated code further requires `Boost C++` libraries installed on your system. If not, please run the command below (if you are using Debian/Ubuntu):

    sudo apt-get install libboost-all-dev

Then we can test a particular benchmark from the smallsuite using one of the commands below:

    make	test_absent_aq
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

These commands will run `reelay` for the specification file in the benchmark and generate a C++ class that monitors the property  in `include` directory. This class are then compiled together with the corresponding (main) application file in `src` directory to generate a monitoring program. Finally it executes the generated program over the trace file in the benchmark.
