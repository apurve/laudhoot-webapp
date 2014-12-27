#!/bin/bash
clear
echo "#### Starting Jetty Server ####"
export GRADLE_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=4040,server=y,suspend=n"
gradle --debug jettyRun
echo "#### Jetty Server Stoped ####"