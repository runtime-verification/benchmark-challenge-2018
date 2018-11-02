The client sends an empty PUT request and prints the body of the server's response, if present.
PUT requests correspond to the most typical case requiring a 204 response.

The correct server sends a 204 response without body.

The buggy server sends a 204 response with a body which, however, cannot be received by the client.
