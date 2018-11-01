# Locking Property

## Informally

_This property concerns the acquisition and release of locks by concurrentlyexecuting threads. It consists of three subproperties:_

  1. _A thread going to sleep must have released all acquired locks before then._ 
  2. _If a thread acquires a lock, no thread may prior have acquired the lock and not yet released it._ 
  3. _A thread cannot release a lock without having acquired it and not yet released it._ 

## Events

| Event              |  Explanation         | 
| ------------------:|:---------------------|
| acq(thread,lock)   | thread acquires lock |
| rel(thread,lock)   | thread releases lock |
| sleep(thread)      | thread goes to sleep |

## Formalization in DejaVu

```
prop locksBasic :
  Forall t . Forall l .
    (
      (sleep(t) -> ![acq(t,l),rel(t,l))) &
      (acq(t,l) -> ! exists s . @ [acq(s,l),rel(s,l))) &
      (rel(t,l) -> @ [acq(t,l),rel(t,l)))
    )
```
         
## Oracle

| Trace          |  Verdict                 | 
| --------------:|:-------------------------|
| log-10k.txt    | fails on event 10,401    |
| log-100k.txt   | fails on event 105,001   |
| log-1000k.txt  | fails on event 1,050,126 |

