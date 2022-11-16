#!/bin/bash

CPATH=".:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar"
DIR="submission"
TESTJAVA="TestListExamples.java"
TESTOBJ="${TESTJAVA%.*}"
FILENAME="ListExamples.java"
# redirect std error
COMPILEINFO=".compile.error"
TESTINFO=".unittest.error"
GITINFO=".git.error"

# git clone
if [[ -d $DIR ]]; then
    rm -rf $DIR
fi
git clone $1 $DIR > /dev/null 2>$GITINFO
if [[ $? -ne 0 ]]; then
    cat $GITINFO
    rm $GITINFO
    exit 3
fi
rm $GITINFO
# file exist
cp $TESTJAVA $DIR/
cd $DIR
if [[ ! -f $FILENAME ]]; then
    echo "$FILENAME not found"
    exit 2
fi
# compile
javac -cp $CPATH *.java 2> $COMPILEINFO
if [[ $? -ne 0 ]]; then
    echo -e "Compile error."
    echo -e "\ndetails:"
    cat $COMPILEINFO
    exit 1
fi
# run
java -cp $CPATH org.junit.runner.JUnitCore $TESTOBJ > $TESTINFO
# result
result=`grep '^[\.E]\+$' < .unittest.error`
error=`grep -o 'E' <<<"$result" | grep -c .`
total=`grep -o '\.' <<<"$result" | grep -c .`
echo "$((total-error))/$total passed."
echo -e "\ndetails:"
cat $TESTINFO
