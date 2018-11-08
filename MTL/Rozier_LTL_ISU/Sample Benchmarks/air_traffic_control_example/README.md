## Automatic Air Traffic Control System Example

The example contained here was generated using requirements drawn directly from [1], with slight modifications made to account for differences in specification logic.

```
((-(CTR_control & G[1,1](TSAFE_ALERT_NON)) | G[1,1] CTR_control) &
    (-TSAFE_snt_cmd | G[1,1](TSAFE_cmd)) & 
    (-(TSAFE_res & TSAFE_ALERT_BT) | G[1,1](TSAFE_cmd)) & 
    (-(TSAFE_ALERT_BT & CTR_control) | G[1,1](-CTR_control)) & 
    (-(ac_TSAFE_cmd_done) | G[1,1](CTR_control)) & 
    (-(ac_TCAS_cmd & -ac_TCAS_cmd_done) | (-ac_TSAFE_cmd_done U[0,end] ac_TCAS_cmd_done)) & 
    (-(-TSAFE_ALERT_NON) | F[0,3](TSAFE_ALERT_NON)) & 
    (-(TSAFE_control) | F[0,3](-TSAFE_control)) & 
    (-(-controller_CTR_control_one & -ac_one_CTR_command_done) | ((-ac_one_CTR_command_done U[0,end] controller_CTR_control_one) | F[0,end](ac_one_CTR_command_done))) &
    ((-(TSAFE_control) | -CTR_control) & (!(CTR_control) | !TSAFE_control)) &
    (-(ac_one_TSAFE_cmd & -ac_two_TSAFE_cmd) | (-ac_two_TSAFE_cmd_done U[0,end] ac_one_TSAFE_cmd_done)) &
    F[0, end](-TSAFE_ALERT_AT) &
    ((TSAFE_ALERT_NON & -TSAFE_ALERT_AT & -TSAFE_ALERT_BT) | (-TSAFE_ALERT_NON & TSAFE_ALERT_AT & -TSAFE_ALERT_BT) | (-TSAFE_ALERT_NON & -TSAFE_ALERT_AT & TSAFE_ALERT_BT)) &
    (-INITIAL | G[10,10](TSAFE_ALERT_AT & TSAFE_ALERT_BT))
)
```

These specifications compose core requirements of an automated air traffic control system. Note that in the line ```(-INITIAL | G[10,10](TSAFE_ALERT_AT & TSAFE_ALERT_BT)``` a fault is intentionally triggered, in which the mutual exclusivity of ```TSAFE_ALERT_BT```, ```TSAFE_ALERT_AT```, and ```TSAFE_ALERT_NON``` (as expressed in the previous line) is violated. Thus, in the sample trace generated, the oracle reports ```false``` at time step 10. More complex errors may be easily generated using this structure, in which the error is "scheduled" relative to t = 0, using the ```INITIAL``` macro.

## References
[1] Zhao, Yang, and Rozier, Kristin Yvonne. “Formal Specification and Verification of a Coordination Protocol for an Automated Air Traffic Control System.” In 12th International Workshop on Automated Verification of Critical Systems (AVoCS2012), volume 53 of Electronic Communications of the EASST, 2012.
