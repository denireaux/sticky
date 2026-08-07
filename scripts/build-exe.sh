#!/bin/usr/env bash

set -e

cd ..

jpackage \
  --type app-image \
  --name Sticky \
  --input . \
  --main-jar Sticky.jar \
  --main-class com.denireaux.sticky.Sticky \
  --dest output