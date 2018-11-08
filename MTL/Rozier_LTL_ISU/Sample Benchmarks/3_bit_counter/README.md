## 3-bit Counter Example

The example contained here was generated using an explicit description of the state changes for a 3-bit counter, via the following formula:

```
(
    (-INITIAL | (-a & -b & -c)) & 
    (-(-a & -b & -c) | G[1,1](-a & -b & c)) &
    (-(-a & -b & c) | G[1,1](-a & b & -c)) &
    (-(-a & b & -c) | G[1,1](-a & b & c)) &
    (-(-a & b & c) | G[1,1](a & -b & -c)) &
    (-(a & -b & -c) | G[1,1](a & -b & c)) &
    (-(a & -b & c) | G[1,1](a & b & -c)) &
    (-(a & b & -c) | G[1,1](a & b & c)) &
    (-(a & b & c) | G[1,1](-a & -b & -c))
)
```

Note that, via the subformula ```(-INITIAL | (-a & -b & -c))```, the initial state of the counter may be specified. This also allows for the scheduling of fault insertions. For example, the subformula ```(-INITIAL | ((-a & -b & -c) & G[8,8](-a & b & -c)))``` would generate a correctly incrementing counter until time step 8, at which point the values of a,b, and c would be forced, and the oracle falsified. 
