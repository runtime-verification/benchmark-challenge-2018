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

# instrument & go
DIR="$PWD"/`dirname "$0"`
npm run --silent jalangi -- --initParam names:[\"fs.open\",\"fs.write\",\"fs.close\"] --initParam func_post:true --initParam log:"$PWD/$1" --inlineIID --inlineSource --analysis instrumentation/functionInvocationAnalysis.js "$DIR"/wrong.js
