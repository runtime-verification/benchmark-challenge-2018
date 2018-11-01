# Deadlock Property

## Informally

_The Deadlock property states that any two threads are not allowed to acquire any two locks in opposite order. That is, if a thread t1 acquires a lock l1, and then before releasing it, acquires a lock l2, then another thread t2 is not allowed to first acquire l2 and then, before releasing it, acquire l1. Following this discipline prevents cyclic deadlocks. Note that a violation of this property in a trace only indicates that the monitored application has a potential for deadlocking, not necessarily an actual deadlock._

## Events

| Event              |  Explanation         | 
| ------------------:|:---------------------|
| acq(thread,lock)   | thread acquires lock |
| rel(thread,lock)   | thread releases lock |

## Formalization in DejaVu

```
prop locksDeadlocks :
  Forall t1 . Forall t2 . Forall l1 . Forall l2 .
    (@ [acq(t1,l1),rel(t1,l1)) & acq(t1,l2))
    ->
    (! @ P (@ [acq(t2,l2),rel(t2,l2)) & acq(t2,l1)))
```
         
## Oracle

| Trace          |  Verdict                 | 
| --------------:|:-------------------------|
| log-10k.txt    | fails on event 9,606     |
| log-100k.txt   | fails on event 100,006   |
| log-1000k.txt  | fails on event 1,050,006 |
