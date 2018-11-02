'use strict';

const http = require('http');

const server = http.createServer((req, res) => {
    req.setEncoding('utf8');
    req.on('data',console.log);
    req.on('end',()=>res.end());
});

server.listen(8080);
