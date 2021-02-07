#!/usr/bin/env bash
nohup java -jar $1 > $2 &
kill -9 $( ps -ef | grep $3 | grep -v "grep" | awk '{print $2}')
