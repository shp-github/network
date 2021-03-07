#!/usr/bin/env bash
nohup java -Dloader.path=\lib -jar $2 > $3 -Xmn10240m -Xmx40960m &
kill -9 $1
