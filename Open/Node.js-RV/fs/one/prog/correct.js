var fs = require('fs')
var MAX = 3

function error(err) {
	if (err) {
		error.log(err)
		process.exit(1)
	}
}

function cb(err, fd, i) {
	error(err)
	if (i <= MAX)
		fs.write(fd, String(i), function (err) {cb(err, fd, i+1)})
	else
		fs.close(fd, error)
}

fs.open('out.txt', 'w', function (err, fd) {cb(err, fd, 1)})
