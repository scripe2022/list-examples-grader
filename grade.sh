CPATH=".:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar"
DIR="submission"
TESTJAVA="TestListExamples.java"
TESTOBJ="${TESTJAVA%.*}"
FILENAME="ListExamples.java"
COMPILEINFO=".compile.error"
TESTINFO=".unittest.error"

rm -rf $DIR
git clone $1 $DIR > /dev/null 2>&1
if [[ $? -ne 0 ]]; then
    exit
fi
cp $TESTJAVA $DIR/
cd $DIR
if [[ ! -f $FILENAME ]]; then
    echo "$FILENAME not found"
    exit 2
fi
javac -cp $CPATH *.java 2> $COMPILEINFO
if [[ $? -ne 0 ]]; then
    echo -e "Compile error.\n"
    cat $COMPILEINFO
    exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore $TESTOBJ > $TESTINFO

result=`grep '^[.E]\+$' < .unittest.error`
total=`grep -o '.' <<<"$result" | grep -c .`
error=`grep -o 'E' <<<"$result" | grep -c .`
echo "$((total-error))/$total passed."
echo -e "\ndetails:"
cat $TESTINFO