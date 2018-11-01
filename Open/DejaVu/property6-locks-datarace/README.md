# Datarace Property

## Informally

_The Datarace property captures data race potentials. A data race occurs when two threads access (read or write) the same shared variable simultaneously, and at least one of the threads writes to the variable. The property states that if a variables is acccessed by two threads, and one writes to the variable, there must exist a lock, which both threads hold whenever they access the variable._

## Events

| Event              |  Explanation           | 
| ------------------:|:-----------------------|
| acq(thread,lock)   | thread acquires lock   |
| rel(thread,lock)   | thread releases lock   |
| read(thread,var)   | thread reads variable  |
| write(thread,var)  | thread writes variable |

## Formalization in DejaVu

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
         
## Oracle

| Trace          |  Verdict                 | 
| --------------:|:-------------------------|
| log-10k.txt    | fails on event 10,005    |
| log-100k.txt   | fails on event 100,005   |
| log-1000k.txt  | fails on event 1,050,005 |

