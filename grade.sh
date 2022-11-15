CPATH=".:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar"
DIR="submission"
TESTJAVA="TestListExamples.java"
TESTOBJ="${TESTJAVA%.*}"
rm -rf $DIR
git clone $1 $DIR
cp $TESTJAVA $DIR/

cd $DIR
javac -cp $CPATH *.java 2> compile.error
if [[ $? -ne 0 ]]; then
    file_not_found=`grep "file not found" compile.error`
    if [[ -z "$file_not_found" ]]; then
        echo "Compile error."
    else
        echo "File not found."
    fi
    `cat compile.error`
    exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore $TESTOBJ
