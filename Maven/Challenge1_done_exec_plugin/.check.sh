#!/bin/sh
SCORE=0
rm .output.txt
mvn exec:java -Dexec.args=10 | tee .output.txt
TEST_1=$(grep -io -e "maven-compiler-plugin" -e "exec-maven-plugin" -e "javaApp.EvenSum" pom.xml | wc -l)
TEST_2=$(grep -io "BUILD SUCCESS" .output.txt | wc -l)
TEST_3=$(grep -io -e "2,4,6,8,10" -e "30" .output.txt | wc -l)

if [ "$TEST_1" -eq 3 ]
then SCORE=$(($SCORE + 35))
fi;
if [ "$TEST_2" -eq 1 ]
then SCORE=$(($SCORE + 25))
fi;
if [ "$TEST_3" -eq 2 ]
then SCORE=$(($SCORE + 40))
fi;
echo "FS_SCORE:$SCORE%" 
