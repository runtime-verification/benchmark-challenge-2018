The server processes HTTP requests by collecting their bodies, and prints 'ok' when all body chunks have been received ('end' event).

The correct client sends a POST request where content-length field is correctly set to the size of the body, then receives an 'ok' response from the server and terminates.

In the wrong client the content-length field is set to a number lower than the correct size, therefore the body is received only partially.
