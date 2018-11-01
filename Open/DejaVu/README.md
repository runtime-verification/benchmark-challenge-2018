
# The DejaVu Benchmark

## Contributors

* Klaus Havelund, Jet Propulsion Laboratory, California, USA
* Doron Peled, Bar Ilan University, Israel
* Dogan Ulus, Verimag/Universite Grenoble-Alpes Grenoble, France

## Introduction

This benchmark contains artifacts (properties and traces) that have been used to evaluate the DejaVu monitoring tool. Dejavu is a monitoring tool for first-order past time temporal logic using BDDs for storing data values observed in a trace. The benchmark consists of six properties, formalized in DejaVu, and for each property a collection of traces varying in size from approximatey 5,000 events to approximately one million events. 

## DejaVu Code and Papers

DejaVu is available here:

    https://github.com/havelund/dejavu

See the documentation there (the top level README.md file) for an explanation of the DejaVu logic.

The following papers on Dejavu are included in the repository:

1. _Runtime Verification: From Propositional to First-Order Temporal Logic (Tutorial)_, K. Havelund and D. Peled, 18th International Conference on Runtime Verification (RV 2018), 10-13 November 2018 - Limassol, Cyprus. LNCS TBD.

2. _Efficient Runtime Verification of First-Order Temporal Properties_, K. Havelund and D. Peled , 25th International Symposium on Model Checking of Software (SPIN 2018), 20-22 June, 2018, Malaga, Spain. LNCS Volume 10869. 

3. _First Order Temporal Logic Monitoring with BDDs_, K. Havelund, D. Peled, and D. Ulus, 17th Conference on Formal Methods in Computer-Aided Design (FMCAD 2017), 2-6 October, 2017, Vienna, Austria. IEEE. 


## Repository Structure

The repository contains the following entries:

* This ``README.md`` file.
* Six property folders, each containing traces for a particular property:
  - ``property1-access``
  - ``property2-file``
  - ``property3-fifo``
  - ``property4-locks-basic``
  - ``property5-locks-cycles``
  - ``property6-locks-datarace``
* A ``specification.txt`` file summarizing the properties.
* An ``oracle.txt`` file summarizing the verdicts expected for combinations of properties and traces.
* A ``papers`` folder containing papers on DejaVu.

Each property folder contains:

* A ``README.md`` file explaining:
  - the property in English.
  - the event format.
  - the formalization in the DejaVu logic.
  - for each provided trace what event (its position) causes it to fail. All properties are violated on these traces on one of the last events in the trace.
* A file ``prop.dejavu`` containing the formalization of the property.
* A collection of traces of the form ``log-<number>k.csv``, where ``<number>`` indicates how many k (thousand) events the trace approximately contains. So ``log-10k.csv`` contains approximately 10,000 events.

## Trace Format

The traces are represented in CSV (Comma Separated Value) format with **no** headers. Concerning CSV format see e.g.:

    https://en.wikipedia.org/wiki/Comma-separated_values 
    
For example a trace consisting of the following five events:
 
    login(John)
    open(file2)
    access(John,file2)
    close(file2)
    logout(John)
    
is represented as the following CSV file:

    login,John
    open,file2
    access,John,file2
    close,file2
    logout,John  
    
## Properties

In the following we present the six properties informally, the events they concern, and their formalization in DejaVu.

### Property 1 : Access

#### Informal

_The Access property states that if a file is accessed by a user, then the user should have logged in and not yet logged out, and the file should have been opened and not yet closed._ 

#### Events

| Event              |  Explanation       | 
| ------------------:|:-------------------|
| access(user,file)  | user accesses file |
| login(user)        | user logs in       |
| logout(user)       | user logs out      |
| open(file)         | file is opened     |
| close(file)        | file is closed     |

#### Formal

```
prop access :
  Forall user . Forall file .
    access(user,file) ->
      [login(user),logout(user))
        &
      [open(file),close(file))
```


### Property 2 : File

#### Informal

_The File property states that if a file is closed, then it must have been opened (and not yet closed) with some mode m (e.g. read or write)._ 

#### Events

| Event              |  Explanation       | 
| ------------------:|:-------------------|
| open(file,mode)    | file is opened with a particular mode (e.g, read or write) |
| close(file)        | file is closed     |

#### Formal

```
prop file : Forall f . close(f) -> Exists m . @ [open(f,m),close(f))
```

### Property 3 : FIFO

#### Informal

_The FIFO property is a conjunction of four subproperties about data entering and exiting a queue:_ 

  1. _A datum can enter the queue at most once._
  2. _A datum can exit the queue at most once._
  3. _A datum can only exit if it has previously been entered._
  4. _The queue has to respect the FIFO principle._

#### Events

| Event              |  Explanation                     | 
| ------------------:|:---------------------------------|
| enter(x)           | datum x is entered into the queue|
| exit(x)            | datum x is removed from the queue|

#### Formal

```
prop fifo :
 Forall x .
  (enter(x) -> ! @ P enter(x)) &
  (exit(x) -> ! @ P exit(x)) &
  (exit(x) -> @ P enter(x)) &
  (Forall y . (exit(y) & P (enter(y) & @ P enter(x))) -> @ P exit(x))
```

### Property 4 : Locks

#### Informal

_This property concerns the acquisition and release of locks by concurrentlyexecuting threads. It consists of three subproperties:_

  1. _A thread going to sleep must have released all acquired locks before then._ 
  2. _If a thread acquires a lock, no thread may prior have acquired the lock and not yet released it._ 
  3. _A thread cannot release a lock without having acquired it and not yet released it._ 

#### Events

| Event              |  Explanation         | 
| ------------------:|:---------------------|
| acq(thread,lock)   | thread acquires lock |
| rel(thread,lock)   | thread releases lock |
| sleep(thread)      | thread goes to sleep |

#### Formal

```
prop locksBasic :
  Forall t . Forall l .
    (
      (sleep(t) -> ![acq(t,l),rel(t,l))) &
      (acq(t,l) -> ! exists s . @ [acq(s,l),rel(s,l))) &
      (rel(t,l) -> @ [acq(t,l),rel(t,l)))
    )
```

### Property 5 : Deadlocks

#### Informal

_The Deadlock property states that any two threads are not allowed to acquire any two locks in opposite order. That is, if a thread t1 acquires a lock l1, and then before releasing it, acquires a lock l2, then another thread t2 is not allowed to first acquire l2 and then, before releasing it, acquire l1. Following this discipline prevents cyclic deadlocks between two threads. Note that a violation of this property in a trace only indicates that the monitored application has a potential for deadlocking (in a difference schedule), not that it is necessarily deadlocking in the observed schedule._

#### Events

| Event              |  Explanation         | 
| ------------------:|:---------------------|
| acq(thread,lock)   | thread acquires lock |
| rel(thread,lock)   | thread releases lock |

#### Formal

```
prop locksDeadlocks :
  Forall t1 . Forall t2 . Forall l1 . Forall l2 .
    (@ [acq(t1,l1),rel(t1,l1)) & acq(t1,l2))
    ->
    (! @ P (@ [acq(t2,l2),rel(t2,l2)) & acq(t2,l1)))
```

### Property 6 : Dataraces

#### Informal

_The Datarace property captures data race potentials. A data race occurs when two threads access (read or write) the same shared variable simultaneously, and at least one of the threads writes to the variable. The property states that if a variable is acccessed by two threads, and one writes to the variable, there must exist a lock, which both threads hold whenever they access the variable._

#### Events

| Event              |  Explanation           | 
| ------------------:|:-----------------------|
| acq(thread,lock)   | thread acquires lock   |
| rel(thread,lock)   | thread releases lock   |
| read(thread,var)   | thread reads variable  |
| write(thread,var)  | thread writes variable |

#### Formal

```
prop locksDataraces :
  Forall t1 . Forall t2 . Forall x .
    (
      (P (read(t1,x) | write(t1,x)))
      &
      (P write(t2,x))
    )
    ->
    Exists l .
      (
        H ((read(t1,x) | write(t1,x)) -> [acq(t1,l),rel(t1,l)))
        &
        H ((read(t2,x) | write(t2,x)) -> [acq(t2,l),rel(t2,l)))
      )
```

## Executing the Benchmark with DejaVu

The traces can be analyzed with DejaVu by following the follwing instructions.

### Step 1

* Install dejavu : https://github.com/havelund/dejavu 

### Step 2

Say you want to analyze traces in the folder ``property1-access``. Do a:

    cd property1-access

To run dejavu on the log with around 10,000 events do:

    dejavu prop.dejavu log-10k.csv

DejaVu represents each variable with _N_ BDD bits, the default is _N_ = 20 bits per variable. _N_ bits allows for _2^N_ different data values to be observed. However, DejaVu performs garbage collection, so the exact number of values observable may be bigger than _2^N_. The smaller the number of bits the more efficient DejaVu is. However, too few bits may cause DejaVu to run out of memory.

The bit number _N_ can be changed by providing the desired bit number to the dejavu command. E.g. if we want to try with 13 bits, it will look as follows:

    dejavu prop.dejavu log-10k.csv 13
        

## Results

The resuls of checking the traces against the properties with DejaVu are shown below. The evaluation was performed
on a Mac laptop, with the Mac OS X 10.10.5 operating system, on a 2.8 GHz Intel Core i7 with 16 GB of memory. The evaluation was performed with different number of BDD bits representing quantified variables occuring in the properties. In case less than 20 bits were used, the actual number is indicated in a parenthesis, e.g.: (13b) means 13 bits used.

### Property 1 : Access

| Trace         |  bits < 20       |  bits = 20  | bits = 40  | bits = 60   | 
| -------------:|:----------------:|:-----------:|:----------:|:-----------:|
| 10K           |  0m0.882s (13b)  | 0m0.862s    | 0m0.966s   | 0m0.991s    | 
| 100K          |  0m2.057s (16b)  | 0m1.963s    | 0m2.769s   | 0m3.127s    | 
| 1000K         |  0m15.452s (19b) | 0m14.138s   | 0m22.523s  | 0m31.780s   | 


### Property 2 : File

| Trace         |  bits < 20       |  bits = 20  | bits = 40  | bits = 60 | 
| -------------:|:----------------:|:-----------:|:----------:|:---------:|
| 10K           | 0m0.899s (13b)   | 0m0.877s    | 0m0.875s   | 0m0.919s  | 
| 100K          | 0m1.879s (17b)   | 0m1.900s    | 0m1.893s   | 0m2.123s  | 
| 1000K         | no lower works   | 0m8.560s    | 0m9.630s   | 0m10.814s | 

### Property 3 : FIFO

| Trace         |  bits < 20       |  bits = 20  | bits = 40  | bits = 60 | 
| -------------:|:----------------:|:-----------:|:----------:|:---------:|
| 5K            | 0m22.708s (13b)  | 2m22.694s   | **OOM**    | -         |
| 10K           | 4m22.872s (14b)  | **OOM**     | -          | -         | 
| 100K          | **OOM** (14b)    | -           | -          | -         | 
| 1000K         | -                | -           | -          | -         | 

### Property 4 : Locks

| Trace         |  bits < 20       |  bits = 20  | bits = 40  | bits = 60 | 
| -------------:|:----------------:|:-----------:|:----------:|:---------:|
| 10K           | 0m1.164s (10b)   | 0m1.580s    | 0m3.499s   | 0m23.507s | 
| 100K          | 0m2.114s (13b)   | 0m1.988s    | 0m2.145s   | 0m2.575s  | 
| 1000K         | 0m7.661s (8b)    | 0m10.209s   | 0m14.844s  | 0m20.870s |

### Property 5 : Deadlocks

| Trace         |  bits < 20       |  bits = 20  | bits = 40  | bits = 60 | 
| -------------:|:----------------:|:-----------:|:----------:|:---------:|
| 10K           | 0m1.843s (10b)   | 0m6.988s    | 0m27.335s  | 0m54.265s | 
| 100K          | 0m1.548s (13b)   | 0m1.589s    | 0m1.755s   | 0m1.718s  | 
| 1000K         | 0m14.233s (7b)   | 0m47.698s   | 1m55.029s  | 4m1.124s  | 

### Property 6 : Dataraces

| Trace         |  bits < 20       |  bits = 20  | bits = 40  | bits = 60 | 
| -------------:|:----------------:|:-----------:|:----------:|:---------:|
| 10K           | 0m1.030s (6b)    | 0m1.225s    | 0m1.480s   | 0m1.782s  | 
| 100K          | 0m2.071s (10b)   | 0m2.606s    | 0m3.031s   | 0m4.471s  | 
| 1000K         | 0m6.713s (9b)    | 0m6.795s    | 0m7.817s   | 0m8.527s  | 