#!/bin/sh
SCORE=0
TEST_1=$(find /home/user/.jenkins/secrets/initialAdminPassword | wc -l)
TEST_2=$(find /home/user/.jenkins/jobs/PipelineDemo/builds -name build.xml -exec grep -o "SUCCESS" {} \; | wc -l)
TEST_3=$(find /home/user/.jenkins/jobs/PipelineDemo/builds -name junitResult.xml | wc -l)
TEST_4=$(grep -i -o -e "*/5\s\*\s\*\s\*\s\*" -e "JenkinsFile" -e "https://github.com/frescoplaylab/JenkinsPipeline.git" /home/user/.jenkins/jobs/PipelineDemo/config.xml| wc -l)
if [ "$TEST_1" -eq 1 ]
then SCORE=$(($SCORE + 15))
fi;
if [ "$TEST_2" -ge 2 ]
then SCORE=$(($SCORE + 25))
fi;
if [ "$TEST_3" -eq 1 ]
then SCORE=$(($SCORE + 30))
fi;
if [ "$TEST_4" -ge 3 ]
then SCORE=$(($SCORE + 30))
fi;
echo "FS_SCORE:$SCORE%"  