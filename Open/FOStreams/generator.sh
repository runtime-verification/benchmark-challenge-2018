#!/usr/bin/env bash
BASE_DIR=$(dirname "${BASH_SOURCE[0]}")
java -cp "$BASE_DIR/target/evaluation-tools-1.0-SNAPSHOT.jar" ch.ethz.infsec.CsvStreamGenerator "$@"
