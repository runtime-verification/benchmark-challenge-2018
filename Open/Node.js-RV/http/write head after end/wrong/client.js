'use strict';

/*
Class http.ServerResponse
method response.writeHead(statusCode[, statusMessage][, headers])

This method must only be called once on a message and it must be called before response.end() is called.

If response.write() or response.end() are called before calling this, the implicit/mutable headers will be calculated and call this function.

When headers have been set with response.setHeader(), they will be merged with any headers passed to response.writeHead(), with the headers passed to response.writeHead() given precedence.
*/

const http = require('http');

const options = {
	port: 8080
}

const req = http.get(options, res => {
	console.log('received response ' + res.statusCode);
	res.setEncoding('utf8');
	res.on('data', chunk => console.log);
});
