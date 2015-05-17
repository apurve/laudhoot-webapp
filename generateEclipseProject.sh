#!/bin/bash
clear
echo "#### Generating Eclipse Project ####"
gradle cleanEclipse eclipse
echo "#### Completed, updated sources and classpath ####"