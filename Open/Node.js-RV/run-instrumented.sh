#!/usr/bin/env bash

# instrument a JavaScript file on-the-fly, run and log trace
# args:
# - JavaScript file to run
# - Log file for the trace

# check args
if [ $# -ne 2 ]
	then
		echo "expected args: script.js log.txt"
		exit 1
fi

# instrument & go
node node_modules/jalangi2/src/js/commands/jalangi.js --initParam names:[\"fs.open\",\"fs.write\",\"fs.close\"] --initParam func_post:true --initParam log:"$2" --inlineIID --inlineSource --analysis instrumentation/functionInvocationAnalysis.js $1
