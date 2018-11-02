The client sends a GET request and prints the body of the response, if present.

The correct server sends a 304 response without body.

The buggy server sends a 304 response with a body which, however, cannot be received by the client.
