#!/bin/sh
score=0
pass=0
fail=0
rm .output.txt
mvn exec:java -Dexec.args=10 | tee .output.txt
TEST_1=$(grep -io -e "maven-compiler-plugin" -e "exec-maven-plugin" -e "javaApp.EvenSum" pom.xml | wc -l)
TEST_2=$(grep -io "BUILD SUCCESS" .output.txt | wc -l)
TEST_3=$(grep -io -e "2,4,6,8,10" -e "30" .output.txt | wc -l)

if [ "$TEST_1" -eq 3 ]
then ((pass++))
else
    ((fail++))
fi;
if [ "$TEST_2" -eq 1 ]
then ((pass++))
else
    ((fail++))
fi;
if [ "$TEST_3" -eq 2 ]
then ((pass++))
else
    ((fail++))
fi;
score= $(( ($pass * 100) / 3))

echo "Total testcase: 3"
echo "Total testcase passed: $pass"
echo "Total testcase fail: $fail"

echo "total score: $score"
