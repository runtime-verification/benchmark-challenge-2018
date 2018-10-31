Building
========

The following software packages must be installed to build and run the
benchmark tools:

  - Java JDK version 8 or later <https://openjdk.java.net>
  - a current version of Apache Maven <https://maven.apache.org>

Run

    $ mvn package

from the directory that contains this README file. After completion, the tools
can be run using the provided Shell scripts generator.sh and replayer.sh.


Stream Generator
================

Usage:

./generator.sh {-S | -L | -T | -P <pattern>}
               [-e <event rate>] [-i <index rate>] [-t <timestamp>]
               [-x <violation ratio>] [-w <interval>]
               [-pA <event ratio A>] [-pB <event ratio B>]
               [-z <Zipf exponents>] [<trace length>]

Option summary:

    <trace length>
            The length of the trace in seconds. If not specified, an unbounded
            stream is generated.

    -S | -L | -T
            Selects the star, linear, or triangle event pattern (see below).
            Exactly one of these (or -P) must be selected.

    -P <pattern>
            Specifies a custom event pattern. A pattern consists of three event
            names, each followed by a comma-separated list of variables
            enclosed in parentheses.

    -e <event rate>
            The total number of events per second (default: 10).

    -i <index rate>
            The number of indices or time-points per second (default: 1).

    -t <timestamp>
            The initial timestamp used for the first events (default: 0).

    -x <violation ratio>
            The frequency of violations, relative to the total number of
            events (default: 0.01).

    -w <interval>
            The interval in seconds between base and implied events, and
            between implied and forbidden events (default: 10).

    -pA <event ratio A>
            The relative frequency of event type A (default: 0.33).

    -pB <event ratio B>
            The relative frequency of event type B (default: 0.33).

    -z <Zipf exponents>
            Selects the Zipf distribution instead the uniform distribution for
            some variables. The argument is a comma-separated list of
            assignments <var>=<exp>[+<off>], where <var> is the name of
            variable, <exp> is the non-negative exponent of the Zipf
            distribution, and <off> is an optional offset. For example, if
            <off> is 100, the distribution has support {101, 102, ...}, with
            101 being the most frequent value.


The stream generator prints a random stream of timestamped events to the
standard output. The generator produces a finite trace instead if a length is
specified. In either case, the sequence of evence is produced at once and not
as a real-time stream. Simultaneous events are grouped into time-points, which
are also called indices. The number of indices per second can be changed by
specifying option -i. The total number of events per second is set with option
-e.

Events are parametrized by data values. Two events are said to match if the
corresponding data values are equal. There are three types of events:

  - Base events (type A).

  - Implied events (type B). For every base event, a matching implied event is
    added within the specified interval (option -w).

  - Forbidden events (type C). A forbidden event that matches both a B event in
    the preceding interval and an A event before that (within another interval
    of the same size) constitutes a violation.

Events of the three types are generated randomly and independently according
to the event ratios -pA and -pB. The ratio of type C is implied by the ratios
of type A and B because the sum is always 1. By default, all three types are
equally likely. The frequency of violations relative to the number of events
can be set with option -x.

The correspondence of the events' data values must either be specified as
a custom pattern, or selected from a set of built-in patterns. A pattern
consists of names for the three event types, each followed by a non-empty list
variables. The names are used for printing the events. Whenever a variable
appears in multiple places, then those places correspond to each other, and
they must have equal values in a match. A custom pattern is supplies as
a single argument after option -P. For example,

    ./generator.sh -P "e1(x) e2(x,y,z) e3(y,z)" 10

generates a 10 second trace, where the names of the event types A, B, and C are
e1, e2, and e3, respectively. Events e1 have one data values, while events e2
have three. They match if they agree on their first (or only) value. Events e2
and e3 match if the second and third value of e2 are equal to the first and
second value of e3. Event and variable names can be arbitrary alphanumeric
strings, including hyphens and underscores.

The built-in patterns are as follows:

    -S      Star pattern: A(w,x), B(w,y), C(w,z)
    -L      Linear pattern: A(w,x), B(x,y), C(y,z)
    -T      Triangle pattern: A(x,y), B(y,z), C(z,x)

By default, data values are chosen uniformly between 0 and 999 999 999. It is
also possible to select a Zipf distribution per variable. The exponents are
passed as an argument after option -z, together with optional offsets. For
example,

    ./generator.sh -T -z "x=1.5,z=2+100" 10

generates events according to the triangle pattern, where variable x follows
a Zipf distribution with exponent 1.5 and support {1, 2, ...}. Variable z
follows a Zipf distribution with exponent 2 and support {101, 102, ...}.
Variable y has a uniform distribution.

Note that violations always have uniform values to prevent accidental
matchings. For the same reason, Zipf-distributed values of type C events start
at 1 000 001.

The output consists of lines with the following format, each representing
a single event:

    <event name>, tp=<index>, ts=<timestamp>, x0=<value 1>, x1=<value 2>, ...

Indexes and timestamps start at zero. A different starting timestamp can be set
with option -t.


Replayer
========

Usage:

./replayer.sh [-v | -vv] [-a <acceleration>] [-q <buffer size>] [-m]
              [-t <interval>] [-o <host>:<port>] [<file>]

Option summary:

    <file>  Input file with the trace in modified CSV format. If no file name
            is given, reads from stdin.

    -v      Writes a compact report every second to stderr (see below).

    -vv     Writes a verbose report every second to stderr.

    -a <acceleration>
            Specifies the acceleration factor (default: 1). For example,
            a value of 2 will replay the trace twice as fast. Set this to 0 to
            replay the whole trace as quickly as possible.

    -q <buffer size>
            Sets the size of the internal buffer between the reader and writer
            thread (default: 64). Increase this value if -vv repeatedly reports
            underruns.

    -m      Output in MonPoly format. If this option is not given, the modified
            CSV format is used.

    -t <interval>
            Injects the current time into the output at regular intervals. The
            interval is specified in milliseconds. The format of the timestamp
            lines is "###<ts>", where <ts> is a Unix timestamps with
            millisecond resolution. By default, no timestamps are injected.

    -o <host>:<port>
            Opens a TCP server listening on the given host name and port.
            Only a single client is accepted, to which the output is written.
            If this option is not given, writes to stdout.


The replayer reads events from a trace or stream in modified CSV format. This
is the same format that was used in the First International Competition on
Software for Runtime Verification (RV 2014), and which is produced by the
stream generator. The replayer reproduces these events as a real-time stream
according to the events' timestamps. The events for the first timestamp are
written immediately to the output. The events for the next timestamp are
delayed proportionally to the difference of the timestamps, and so on. The
acceleration (the inverse of the proportionality factor) is set with option -a.

By default, the output is written in the same CSV format to standard output. It
is also possible to use the MonPoly format (option -m). If option -o together
with a hostname and port is given, a TCP server listening to that address is
created. The first client connecting to the TCP server will receive the event
stream. The first event is only sent once a client has connected. No more
clients will be accepted afterwards.

The replayer maintains some statistics about the number of events processed. It
also keeps track of the delay between the scheduled event time and the time the
event was eventually written to the output. The latter may be useful if another
process is reading the event stream via a pipe or socket. Use options -v or -vv
to retrieve a report once every second, which is written to standard error. The
reports contain the current, peak, and maximum delay. The current delay is the
delay of the most recent event. The peak delay is the highest delay that has
been observed between the previous and the current measurement. The maximum
delay is the highest delay that has been observed so far. Note that delays are
tracked only up to the operating system's buffer that is associated with the
pipe or socket. The current and peak delay are zero if no event could be issued
in the last second.

The implementation of the replayer uses two threads, one for reading and one
for writing events, which are connected by a queue with limited capacity. If
the queue is drained fully, an underrun occurs and events may not be reproduced
at the appropriate time. The verbose report (-vv) displays the number of
underruns. If this number is non-zero and especially if it is growing, the
queue capacity should be increased with option -q.

The compact report (-v) consists of one line of measurements per second. Each
line consists of the following fields, which are separated by spaces:

  - elapsed time,
  - the current index rate (1/s),
  - the current event rate (1/s),
  - the current delay at the time of the measurement,
  - the peak delay observed between the previous and current measurements,
  - the maximum delay observed so far, and
  - the average delay.

All values are printed in seconds unless noted otherwise.
