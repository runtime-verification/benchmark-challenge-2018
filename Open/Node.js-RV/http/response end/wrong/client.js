const http = require('http');

/*
Class: http.ServerResponse
response.end([data][, encoding][, callback])

This method signals to the server that all of the response headers and body have been sent; that server should consider this message complete. The method, response.end(), *MUST* be called on each response.
*/

const options = {
  port: 8080
};

const req = http.get(options, res => {
  res.setEncoding('utf8');
  res.on('data', console.log);
  res.on('end', () => {
    console.log('done');
  });
});
