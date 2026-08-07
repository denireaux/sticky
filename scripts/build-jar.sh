#!/usr/bin/env bash

set -e

echo "Compiling..."

if [ -d build/ ]; then
  echo "Removing previous build"
  rm -rf build
fi

cd ..

javac -d build $(find src -name "*.java")
jar --create --file Sticky.jar --main-class com.denireaux.sticky.Sticky -C build .

echo "Done"