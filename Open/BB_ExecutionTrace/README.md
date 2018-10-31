## Overview

The following is a  benchmark for the testing of runtime monitors and other trace analysis tools.
The traces are generated using the accompanying AspectJ code available
[here](https://github.com/RacimBoussaha/LifTracer/blob/master/src/LogAspect.aj).

This choice means that users can readily generate new traces for their own Java applications, as well as modify the format
of the traces to fit their monitor.


## Content

The benchmark currently contains 5 trace files:

* Tetris.csv: a trace from a freely available, open-source tetris game
* JavaChat.csv: An instant messaging discussion between several users, over a client-server architecture
* Semaphore.csv: A run of a tutorial program for manipulating semaphores
* FractalTree.csv: The drawing of a Fractal Tree using a graphics editor
* AESenc.csv: A run of short Java program that generates AES encryption keys and encrypts several files. 

## Source code

The java source code used to generate these files is also freely available at:

* [https://github.com/RacimBoussaha/JavaChat-master](https://github.com/RacimBoussaha/JavaChat-master)
* [https://github.com/Fire30/Tetris](https://github.com/Fire30/Tetris)
* [https://www.mkyong.com/java/java-thread-mutex-and-semaphore-example/](https://www.mkyong.com/java/java-thread-mutex-and-semaphore-example/)
* [https://rosettacode.org/wiki/Fractal_tree#Java](https://rosettacode.org/wiki/Fractal_tree#Java)
* [https://github.com/RacimBoussaha/AESenc](https://github.com/RacimBoussaha/AESenc)

## Trace Structure

Each line correspond to either a single method call or method return. A call line is added to the trace whenever a method is called,
with the first line of the trace naturally corresponding to the initial call to the main function. A corresponding return line is 
added when that function returns.

Each line begins with a timestamp, followed by  a unique identifier that links a call to the corresponding return and by the keyword
`call`  or `return`, identifying the line's type.

In case of call line, it will then list the following information, separated by comas: the method's containing class or interface
and its package, a hashcode of the calling object, the method’s return type, it’s name, it’s nesting depth,  the number of parameters,
a list of parameter types (separated by `//`) and a similarly formatted list of parameter values.  A return method lists only the 
return type and value.  

Values of literals, string  and elementary types are provided explicitly but those of objects are provided by references, allowing 
tracking of data objects across the execution. Arrays are prefixed with `[` and construction are suffixed with `<init>`. 
The ids of objects and method calls are computed by AspectJ.
NA denotes an unavailable value. These can occur in the trace for several reasons. Notably, 

* A method with no parameters has NA in the position for both parameter value, and parameter type.
* Likewise, methods that return void have NA in place of return type and value.
* Static method have NA in the place of the id of the calling object.
* Furthermore, since a call lines has several more pieces of information than a return line, every return line is suffixed with NA, 
so that every line of the csv has the same number of field. This choice makes it easier to open the traces files with a wide variety 
of readers than impose this restriction. 

## Properties
The properties associated with the benchmark are detailed in the accompanying proposal file.

## Verification
BeepBeep 3 [1] processor chains that verify most of the above properties are available at
[https://github.com/qbetti/bb-benchmark](https://github.com/qbetti/bb-benchmark)

## References
[1] Sylvain Hallé:
When RV Meets CEP. [RV 2016](https://dblp.org/db/conf/rv/rv2016.html#Halle16): 68-91

