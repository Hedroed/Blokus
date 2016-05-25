@echo off
set /p className= Nom de la class : 
echo Text %className%
java -cp .\class;"dist\junit-4.12.jar";"dist\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore %className%
pause