#!/usr/bin/env bash
BASE_DIR=$(dirname "${BASH_SOURCE[0]}")
cmd=$1
shift

case "$cmd" in
  generator|Generator ) $BASE_DIR/generator.sh "$@";;
  replayer|Replayer ) $BASE_DIR/replayer.sh "$@";;
  oracle|Oracle ) $BASE_DIR/generator.sh "$@" -osig ./tmp.sig -oformula ./tmp.mfotl; monpoly -sig ./tmp.sig -formula ./tmp.mfotl -negate;;
  * ) echo "Invalid command. Try 'generator', 'replayer' or 'oracle'";;
esac
