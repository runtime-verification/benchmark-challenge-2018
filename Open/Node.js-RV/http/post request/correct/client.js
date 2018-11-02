'use strict';

var http = require('http');
var MAX=process.argv[2]||100;

function request(i){
    if(i<=MAX)
	http.request({port:'8080',method:'POST'}).end(String(i),function (){request(i+1)});
    else
	process.exit(0);
}
request(1);
