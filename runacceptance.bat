cd acceptance
javac -cp .;..\build\classes\main GroupOneKeywords.java
java -cp .;..\build\classes\main;C:\RobotFramework\robotframework-2.8.5.jar;C:\robotFramework\swinglibrary-1.8.0.jar org.robotframework.RobotFramework GroupOneTests.txt
cd ..