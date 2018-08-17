#!/bin/sh -e
# postrm script for environmentservice web
#

if [ -f /usr/local/bin/environmentservice-web ]
then
	rm /usr/local/bin/environmentservice-web
fi
