'use strict';

/*
Class http.ServerResponse
method response.write(chunk[, encoding][, callback])

Note that in the http module, the response body is omitted when the request is a HEAD request. Similarly, the 204 and 304 responses must not include a message body.
*/

var http = require('http');

var server = http.createServer(function (req, res) {
	console.log(req.method + ' request received');

	res.writeHead(304);
	// BUG! 204 responses should include no body
	res.end('hello', function () {
		return console.log('body sent!');
	});
});

server.listen(8080);