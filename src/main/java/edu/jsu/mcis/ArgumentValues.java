package edu.jsu.mcis;

import java.util.*;
//import java.util.Scanner;

public class ArgumentValues{
private String argumentName;
private String value;

	public ArgumentValues(String name){
		argumentName = name;
	}
	
	public void addValue(String name){
		value = name;
	}
	
	public String getValue(){
		return value;
	}
	
}