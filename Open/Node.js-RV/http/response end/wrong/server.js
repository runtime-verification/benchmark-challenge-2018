var http = require('http');

var server = http.createServer(function (req, res) {
  res.writeHead(200);
  res.write('okay');
});

server.listen(8080);
