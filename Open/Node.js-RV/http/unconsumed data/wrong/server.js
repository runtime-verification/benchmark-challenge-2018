'use strict';

var http = require('http');

// prepare (a lot of) data to send in replies
var data = String(new Array(10000));

var server = http.createServer(function (req, res) {
	// just send data
	res.end(data);
});

server.listen(8080);
