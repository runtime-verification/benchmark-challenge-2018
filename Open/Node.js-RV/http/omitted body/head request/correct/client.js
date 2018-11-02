'use strict';

/*
Class http.ServerResponse
method response.write(chunk[, encoding][, callback])

Note that in the http module, the response body is omitted when the request is a HEAD request. Similarly, the 204 and 304 responses must not include a message body.
*/

var http = require('http');
var max_requests = Number(process.argv[2]) || 10;

var options = {
	method: 'HEAD',
	port: 8080
};

var time0 = void 0;
var requests = 0;

/* ignores server response */
function request() {
	var req = http.request(options);
	req.end(function () {
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
