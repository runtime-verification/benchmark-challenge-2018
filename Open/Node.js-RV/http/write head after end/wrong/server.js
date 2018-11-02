'use strict';

/*
Class http.ServerResponse
method response.writeHead(statusCode[, statusMessage][, headers])

This method must only be called once on a message and it must be called before response.end() is called.

If response.write() or response.end() are called before calling this, the implicit/mutable headers will be calculated and call this function.

When headers have been set with response.setHeader(), they will be merged with any headers passed to response.writeHead(), with the headers passed to response.writeHead() given precedence.
*/

var http = require('http');

var server = http.createServer(function (req, res) {
	console.log('request received, calling end()...');
	res.end(function () {
		return console.log('response sent');
	});
	// BUG! writing header after calling end() method
	console.log('setting status code to 204...');
	res.writeHead(204);
});

server.listen(8080);