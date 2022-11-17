# Create your grading script here

rm -rf student-submission
git clone $1 student-submission

if [[ $? -ne 0 ]]
then
    echo "git clone failed. "
    exit 2
fi

if [[ ! -f student-submission/ListExamples.java ]]
then
    echo "ListExamples.java does not found. Check your submission. "
    echo "You score is 0. "
    exit 1
fi
cp TestListExamples.java student-submission
cp -r lib student-submission
cp Server.java student-submission
cp GradeServer.java student-submission

cd student-submission

javac -cp ".:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar" *.java

if [[ $? -ne 0 ]]
then
    echo "compiling error! "
    echo "You score is 0. "
    exit 1
fi
java -cp ".:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar" org.junit.runner.JUnitCore TestListExamples > test-msg.txt

grep -iw "" test-msg.txt