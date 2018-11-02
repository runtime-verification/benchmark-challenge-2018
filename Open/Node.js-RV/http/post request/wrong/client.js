'use strict';

var http = require('http');
var MAX = process.argv[2] || 100;

for (var i = 1; i <= MAX; i++) {
    http.request({ port: '8080', method: 'POST' }).end(String(i));
}
process.exit(0);
