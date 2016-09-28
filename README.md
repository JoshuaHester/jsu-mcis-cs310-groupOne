# Software Engineering Group Project: Argument Parser

1.	Purpose
2.	Programs Used
3.	Usage  
 A.	Overview  
	B.	Functions List  
	C.	Using XML Features  
	D.	Using Command Line Arguments   
4.	Project History  
	A.	Objectives  
	B.	Features  
    C. Process
5.	Team Credits


### Purpose
This Argument parser provides a means to filter user input from a command line into usable arguments for a program. The Argument Parser is not a stand-alone program; it must be built into a program which may then be called from the command line.

### Programs Used
[Robot Framework](http://robotframework.org/) 	-  	Acceptance Testing  
[Git](https://git-scm.com/)			-	Version Control  
[JUnit](http://junit.org/junit4/)			-	Unit Testing  
[Gradle](https://gradle.org/)			-	Build Automation  
[Trello](https://trello.com/)   -   Project Management

### Usage
To use the Argument Parser in a program, initialize an ArgumentParser object, set the name of the program the parser is to be used for, and add arguments. You may then use methods to retrieve arguments for use in the rest of the program. An XML file may be used as an alternative method to specify arguments. 

##### Functions List
`ArgumentParser()`	
	Constructor- creates an empty ArgumentParser object.
	
`void setProgramName(String name)`		
	Sets the name of the program.
	
`void setProgramDescription(String desc)`	
	Sets the description of the program for use in help functions.
	
`void addArgument(String argumentName)`	 
	Adds an Argument which must be set in the order it was created.
	
`void addArgument(Argument.DataType type, String argumentName)`	  
	Adds an Argument which must be set in the order it was created. This Argument must 	be of the specified type.
	
`void addFlag(String argumentName)`  
	Adds an Argument which only accepts a Boolean value.
	
`void setRestrictedValue(String argumentName, String ...restrictedValue)`	
	Forces an Argument to only accept specified values.
	
`void addOptionalArgument(Argument.DataType type, String argumentName, String defaultVal)`  
	Adds an Argument which may be specified by using “--<arg>”.
	
`void addRequiredArgument(String argumentName, Argument.DataType type, String defaultVal)`    
	Adds an Argument with a preset default value. This argument is specified the same way 	as an optional Argument. 
	
`void setShortName(String shortName)`  
	Adds an abbreviated name for an Argument. This method must be used on an Argument 	object.
	
`void parse(String s)`  
	Takes the input from the command line and parses the Arguments. Note: in order to use 	this method, first convert the command line text into a string object.
	
`Argument getArgument(String argName)`  
	Returns the Argument object.
	
`Argument getArgumentByShortName(String shortName)`  
	Returns the Argument object with the associated abbreviation.
	
`<T> T getValue()`  
	Returns the value contained within an Argument object. This must be used on an 	Argument object and requires type
casting.

`ArgumentParser loadParser(String fileName)`  
	Loads a list of Arguments from an XML file.
	
`void saveParser(ArgumentParser argumentP, String filename)`  
	Exports the list of Arguments to an XML file.

##### Using XML Features
It is possible to export a list of arguments into an XML file with saveParser, which may then be loaded into the program with loadParser as an alternative to the addArgument functions. 


##### Using Command-Line Arguments
To use a program with Argument Parser, start the program as any java executable and type into the command line any relevant arguments. By default, typing “-h” will display usage information for arguments. Standard arguments are positional. For example, in the VolCal test program, the first argument is length, followed by width and height. Named, optional arguments are also available and may be set in any order. To set an optional argument, proceed the value with “--<argument name>”. Attempting to use an optional argument which does not exist will throw an InvalidArgumentException. 
	 


### Project History
##### Objectives	
The objective of the project was to create a functional command line argument parser using agile methodology with a focus on test driven development.  The project was to be built through the Gradle build automation tool. Acceptance testing was to be performed by Robot Framework. Unit testing was to be performed by JUnit. Code coverage for unit testing was to be established with JaCoCo. Frequent code refactoring sessions were emphasized.
	
##### Features
Desired features were specified on a Trello board maintained by the instructor. The initial goals were to make the Argument Parser so that it could read and store input which could then be retrieved for use in a program. Additional goals were made available as we progressed with the project, including the use of optional named arguments, arguments with a default value, data type specification, help functions, and XML file compatability.
##### Process
The class was divided into four groups, each assigned the same project. We would meet three times each week to engage in pair-programming sessions, with a Scrum meeting occuring once a week. At the start of the project, there was confusion as to what was wanted and we began by hardcoding the project to parse a volume calculator program. After recieving clarification, we restarted and began writing unit tests. Throughout the life of the project, our unit test covered aproximatly 90% of our code. By having so many tests, I became familiar with the effects of bugs. Often we would add code which passed our most recent tests, but caused the program to fail older tests. Acceptance testing proved challenging to learn. Upon understanding, our team was able to help other teams in that area. 

After a few weeks of working on the project, we spent a week working solely on refactoring the code. This practice taught me personally the most about code readability and reusability of any project I worked on. Around that time, I became compelled to fit in as much functionality as possible, devoting numerous hours outside of class to working on the project. Towards the end of the semester, the instructor gave me a leave of absense, saying that I had learned as much as was feasable from the class.


### Team Credits

Sarah Fedyk  
Joshua Hester  
Jarred Lavier  
Cody Hilyer  
Corey Kyle  
Jingtong Dai  
Shannon Bolton



