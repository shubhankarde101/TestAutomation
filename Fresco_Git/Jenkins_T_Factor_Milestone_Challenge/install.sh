#!/bin/sh
if [ -e /projects/challenge/jenkins.war ]; then
    echo "Jenkins war file already exists";
else
    wget http://updates.jenkins-ci.org/download/war/2.231/jenkins.war
fi; 