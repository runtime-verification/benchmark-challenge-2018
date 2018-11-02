var async = require('async'),
    fs = require('fs');

var MAX_FILES = 10,
    MAX_WRITES = 10;

for (var i = 0; i < MAX_FILES; ++i) {
    var filename = '/tmp/out'+i+'.txt';
    fs.open(filename, 'w', function opencb(err, fd) {
        cb(err);
        
        //for (var i = 0; i < MAX_WRITES; ++i)
        //    fs.write(fd, String(i), cb);
        
        var j = 0;
        async.whilst(
            function f1() { return j < MAX_WRITES; },
            function f2(cb) { return fs.write(fd, String(j++), cb); },
            function f3(err) { cb(err); fs.close(fd, cb); }
        );
    });
}

function cb(err) {
    if (err) {
        console.log(err);
        process.exit(1);
    }
}
