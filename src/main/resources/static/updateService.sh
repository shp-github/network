#!/usr/bin/env bash
nohup java -Dloader.path=\lib -jar $1 > $2 -Xmn10240m -Xmx40960m &
kill -9 $3
