@echo off

echo.
echo ###############################################################################
echo # Start - %~n0%~x0 (%0)
echo ###############################################################################
echo.

set PACKAGE=de.fechtelhoff

mvn archetype:generate --batch-mode -DarchetypeGroupId=de.fechtelhoff -DarchetypeArtifactId=maven-spring-wicket-template -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=%PACKAGE% -DartifactId=%1

echo.
echo ###############################################################################
echo # Ende
echo ###############################################################################
echo.

echo on
