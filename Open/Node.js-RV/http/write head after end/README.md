The client sends a HTTP request, then it receives the response and prints it.

The correct server writes the header before calling method `end`.

The buggy server writes the header after calling method `end`; a default header, which is not the correct one, is received by the server.
