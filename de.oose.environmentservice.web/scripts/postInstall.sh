#!/bin/sh -e
# postinst script for environmentservice web
#
# see: dh_installdeb(1)

# for demonstration purposes only! better solution using build.gradle:
# ...
# ospackage{
#   link('/opt/env-service-web/start.sh' '/usr/local/bin/env-service-web')
# ...

ln -s /opt/environmentservice-web/start-service.sh /usr/local/bin/environmentservice-web
chmod +x /usr/local/bin/environmentservice-web
