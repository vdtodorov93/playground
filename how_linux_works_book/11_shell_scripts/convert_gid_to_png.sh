#!/bin/sh
for file in *.gif; do
if [ ! -f $file ]; then
exit
fi
b=$(basename $file .gif)
echo Converting $b.gif to $b.png...
giftopnm $b.gif | pnmtopng > $b.png
done
