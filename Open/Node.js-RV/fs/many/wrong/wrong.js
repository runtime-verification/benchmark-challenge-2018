var fs = require('fs');

var MAX_FILES = 10,
    MAX_WRITES = 10;

for (var i = 0; i < MAX_FILES; ++i) {
    var filename = '/tmp/out'+i+'.txt';
    fs.open(filename, 'w', function (err, fd) {
        cb(err);
        
        for (var i = 0; i < MAX_WRITES; ++i)
            fs.write(fd, String(i), cb);
        
        fs.close(fd, cb);
    });
}

function cb(err) {
    if (err) {
        console.log(err);
        process.exit(1);
    }
}
