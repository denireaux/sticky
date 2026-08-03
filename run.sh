#!/bin/usr/env bash

set -e

STICKYJ_JAVA=src/com/denireaux/sticky
CLASS_DIR=classes/
OUT_DIR=out/

init-application() {
    echo "Initialize application"

    javac -d out src/com/denireaux/sticky/Sticky.java src/com/denireaux/sticky/utils/Settings.java
    java -cp out:config com.denireaux.sticky.Sticky

    echo "INIT"
}

teardown() {
    echo "Teardown application"
    
    rm -rf ${OUT_DIR}/*

    echo "TEARDOWN"
}

if [ -f Sticky.class ]; then
    teardown
    init-application
    teardown
else    
    init-application
    teardown
fi 
