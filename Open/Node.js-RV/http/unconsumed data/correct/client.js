'use strict';

/*
Class: http.ClientRequest and http.request()

If no 'response' handler is added, then the response will be entirely discarded. However, if a 'response' event handler is added, then the data from the response object *must* be consumed, either by calling response.read() whenever there is a 'readable' event, or by adding a 'data' handler, or by calling the .resume() method. Until the data is consumed, the 'end' event will not fire. Also, until the data is read it will consume memory that can eventually lead to a 'process out of memory' error.
*/

var async = require('async'),
    http = require('http');

var options = {
	port: '8080'
};

// make an async request with the given identifier
function request(i, cb) {
	var req = http.request(options, function (res) {
		console.log('STATUS #' + i + ': ' + res.statusCode);
		res.on('data', function (chunk) {});
		res.on('end', function () {
			console.log('No more data in response #' + i + '.');
		});
	});

	req.on('error', function (e) {
		console.error('problem with request #' + i + ': ' + e.message);
	});

	req.end(cb);
}

// do a lot of requests
var max = 1000;
var i = 0;
async.whilst(function () {
	return i < max;
}, function (cb) {
	return request(i++, cb);
});
