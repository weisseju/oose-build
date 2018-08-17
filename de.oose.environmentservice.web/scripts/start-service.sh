#!/bin/sh -e
# postinst script for environmentservice web
#
# see: dh_installdeb(1)

java -jar /opt/environmentservice-web/lib/de.oose.environmentservice.web-${version}-bootapp.jar


