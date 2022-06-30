#!/bin/bash

echo
echo "###############################################################################"
echo "# Start"
echo "###############################################################################"
echo

if [ -z "$1" ]
then
  ARTIFACT="test"
else
  ARTIFACT="$1"
fi

if [ -z "$2" ]
then
  GROUP="de.fechtelhoff"
else
  GROUP="$2"
fi

echo "Artifact ID = ${ARTIFACT}"
echo "Group ID = ${GROUP}"
echo

mvn archetype:generate --batch-mode -DarchetypeGroupId=de.fechtelhoff -DarchetypeArtifactId=maven-spring-wicket-template -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=${GROUP} -DartifactId=${ARTIFACT}

echo
echo "###############################################################################"
echo "# Ende"
echo "###############################################################################"
echo

exit
