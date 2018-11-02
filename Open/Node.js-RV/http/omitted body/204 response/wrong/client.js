'use strict';

/*
Class http.ServerResponse
method response.write(chunk[, encoding][, callback])

Note that in the http module, the response body is omitted when the request is a HEAD request. Similarly, the 204 and 304 responses must not include a message body.
*/

const http = require('http');
const max_requests=Number(process.argv[2]) || 10

const options = {
	method: 'PUT',
	port: 8080
};

let time0
let requests=0

/* ignores server response */ 
function request(){
    let req=http.request(options)
    req.end(() => {
	requests++
	if(requests<max_requests)
	    setTimeout(request,0)
	else {
	    let time=(Date.now()-time0)/1000
	    console.log(`Requests: ${requests} Time (sec):${time} RPS: ${max_requests/time}`)
	}
    })
    req.on('error', e => console.error(e.message))
}

time0=Date.now()
request()
