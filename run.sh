#!/bin/usr/env bash

set -e

CLASS_DIR=classes/
OUT_DIR=out/

init-application() {
    echo "Initialize application"

    if [ ! -d "saved/" ]; then 
        mkdir -p saved
    fi

    javac -d out $(find src -name "*.java")
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
