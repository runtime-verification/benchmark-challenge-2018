The server receives an HTTP request and prints its body, if present,  on the stdout

The correct client sends a HEAD request without body, as specified in the documentation

The buggy client sends a HEAD request with a body which, however, cannot be received by the server
