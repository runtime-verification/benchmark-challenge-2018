# Access Property

## Informally

_The Access property states that if a file is accessed by a user, then the user should have logged in and not yet logged out, and the file should have been opened and not yet closed._ 

## Events

| Event              |  Explanation       | 
| ------------------:|:-------------------|
| access(user,file)  | user accesses file |
| login(user)        | user logs in       |
| logout(user)       | user logs out      |
| open(file)         | file is opened     |
| close(file)        | file is closed     |

## Formalization in DejaVu

```
prop access :
  Forall user . Forall file .
    access(user,file) ->
      [login(user),logout(user))
        &
      [open(file),close(file))
```
         
## Oracle

| Trace          |  Verdict                 | 
| --------------:|:-------------------------|
| log-10k.txt    | fails on event 11,006    |
| log-100k.txt   | fails on event 110,006   |
| log-1000k.txt  | fails on event 1,100,006 |

         
        