The client sends a GET request, and prints 'ok' and terminates after it has received the server response.

The correct server invokes the 'end' method to complete the response which is correctly received by the client.

The buggy server does not close the response by calling the 'end' method, therefore the client cannot terminate.
