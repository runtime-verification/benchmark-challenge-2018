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

# start server first
node "$DIR"/server.js &
sleep 1

# instrument & go
npm run --silent jalangi -- --initParam names:[\"end\",\"http.request\"] --initParam func_post:true --initParam log:"$PWD/$1" --inlineIID --inlineSource --analysis instrumentation/functionInvocationAnalysis.js "$DIR"/client.js

# kill server
pkill node
