#!/usr/bin/env bash

# verify a trace against a specification
# args:
# - trace as a text file with JSON data
# - Prolog specification

# check args
if [ $# -ne 2 ]
	then
		echo "expected args: trace.txt specification.pl"
		exit 1
fi

# run SWI-Prolog
swipl -p node=oracle oracle/oracle.pl -- "$1" "$2"
