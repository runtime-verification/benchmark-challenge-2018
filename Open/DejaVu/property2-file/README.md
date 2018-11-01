# File Property

## Informally

_The File property states that if a file is closed, then it must have been opened (and not yet closed) with some mode m (e.g. read or write)._ 

## Events

| Event              |  Explanation       | 
| ------------------:|:-------------------|
| open(file,mode)    | file is opened with a particular mode (e.g, read or write) |
| close(file)        | file is closed     |


## Formalization in DejaVu

```
prop file : Forall f . close(f) -> Exists m . @ [open(f,m),close(f))
```
         
## Oracle

| Trace          |  Verdict                 | 
| --------------:|:-------------------------|
| log-10k.txt    | fails on event 11,004    |
| log-100k.txt   | fails on event 110,004   |
| log-1000k.txt  | fails on event 1,100,004 |

