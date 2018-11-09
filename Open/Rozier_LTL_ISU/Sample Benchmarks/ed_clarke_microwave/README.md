## Ed Clarke's Microwave Example

The example contained here was generated using an explicit description of the state changes for the classic "Ed Clarke Microwave" example, from the formula:

```
(
    (-INITIAL | (-Start & -Close & -Heat & -Error)) &
    (-(-Start & -Close & -Heat & -Error) | G[1,1]((Start & -Close & -Heat & Error) | (-Start & Close & -Heat & -Error))) &
    (-(Start & -Close & -Heat & Error) | G[1,1](Start & Close & -Heat & Error)) &
    (-(-Start & Close & -Heat & -Error) | G[1,1]((-Start & -Close & -Heat & -Error) | (Start & Close & -Heat & -Error))) &
    (-(-Start & Close & Heat & -Error) | G[1,1]((-Start & Close & -Heat & -Error) | (-Start & -Close & -Heat & -Error) | (-Start & Close & Heat & -Error))) &
    (-(Start & Close & -Heat & Error) | G[1,1]((Start & -Close & -Heat & Error) | (-Start & Close & -Heat & -Error))) &
    (-(Start & Close & -Heat & -Error) | G[1,1](Start & Close & Heat & -Error)) &
    (-(Start & Close & Heat & -Error) | G[1,1](-Start & Close & Heat & -Error))
)

```

As explained in the case of the 3-bit counter example, initial states and faults may be specified using the "INITIAL" macro, which holds only in the first time step of the trace.
