#!/bin/bash

docker run -p 5000:5000 -e SETTINGS_FLAVOR=local -e STORAGE_PATH=/registry -v /tmp/registry:/registry registry
