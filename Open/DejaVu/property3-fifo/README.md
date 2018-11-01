# FIFO Property

## Informally

_The FIFO property is a conjunction of four subproperties about data entering and exiting a queue:_ 

  1. _A datum can enter the queue at most once._
  2. _A datum can exit the queue at most once._
  3. _A datum can only exit if it has previously been entered._
  4. _The queue has to respect the FIFO principle._


## Events

| Event              |  Explanation                     | 
| ------------------:|:---------------------------------|
| enter(x)           | datum x is entered into the queue|
| exit(x)            | datum x is removed from the queue|

## Formalization in DejaVu

```
prop fifo :
 Forall x .
  (enter(x) -> ! @ P enter(x)) &
  (exit(x) -> ! @ P exit(x)) &
  (exit(x) -> @ P enter(x)) &
  (Forall y . (exit(y) & P (enter(y) & @ P enter(x))) -> @ P exit(x))
```
         
## Oracle

| Trace          |  Verdict              | 
| --------------:|:----------------------|
| log-5k.txt     | fails on event 5,051  |
| log-10k.txt    | fails on event 10,101 |
         
## Note

This property is particulary resource demanding to monitor due to specifically the fourth subproperty (subformula).