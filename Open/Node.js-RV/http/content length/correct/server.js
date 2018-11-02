'use strict';

const http = require('http');

const server = http.createServer((req, res) => {
	console.log('header content-length: %i', req.headers['content-length']);
	req.on('data', chunk => console.log('received chunk of length %i', chunk.length));
	req.on('end', () => res.end('ok'));
});

server.listen(8080);
