In a Linux machine, first launch the oracle (ICE Solution folder in the Component 3) and once it is working, launch the executable CRESCvSoSFinal2.

Once the execution starts, it asks for the number of events that you want to send to the system. Those events (random events) will be sent to the software components (state machines) of the controller. Depending the current status of each of the components they might have to perform transitions. In this case, they will send traces to the oracle.

Output: 

Traces files of each of the components.
In the oracle, you will find the files with the detected errors (Errors.csv) and the status of the components during the execution (Status.csv)