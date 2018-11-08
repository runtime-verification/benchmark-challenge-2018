#!/usr/bin/env bash

# instrument a JavaScript file on-the-fly, run and log trace
# arg:
# - Log file for the trace

# check args
if [ $# -ne 1 ]
	then
		echo "expected arg: log.txt"
		exit 1
fi

DIR="$PWD"/`dirname "$0"`

# start server first, with instrumentation
npm run --silent jalangi -- --initParam names:[\"http.createServer\",\"write\",\"writeHead\",\"end\"] --initParam func_post:true --initParam log:"$PWD/$1" --inlineIID --inlineSource --analysis instrumentation/functionInvocationAnalysis.js "$DIR"/server.js &
sleep 3

# run client
node "$DIR"/client.js

# kill server
pkill node