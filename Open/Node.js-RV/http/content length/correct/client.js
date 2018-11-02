'use strict';

/*
Class: http.ClientRequest

Note: Node.js does not check whether Content-Length and the length of the body which has been transmitted are equal or not.
*/

var max_requests = Number(process.argv[2]) || 10;
var http = require('http');

var data = "hello, world!";

var options = {
	headers: {
		'content-length': Buffer.byteLength(data)
	},
	method: 'POST',
	port: '8080'
};

var time0 = void 0;
var requests = 0;

/* ignores server response */
function request() {
	var req = http.request(options);
	req.end(data, function () {
		requests++;
		if (requests < max_requests) setTimeout(request, 0);else {
			var time = (Date.now() - time0) / 1000;
			console.log('Requests: ' + requests + ' Time (sec):' + time + ' RPS: ' + max_requests / time);
			process.exit();
		}
	});
	req.on('error', function (e) {
		return console.error(e.message);
	});
}

time0 = Date.now();
request();
