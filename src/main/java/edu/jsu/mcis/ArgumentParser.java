package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser{
	private String programName;
	private Vector<ArgumentValues> argumentList; 
	
	public ArgumentParser(){
		argumentList = new Vector<ArgumentValues>();
	}
	
	public void addArgument(String argumentName){
		argumentList.add(new ArgumentValues(argumentName));
	}
	
	private void addValue(String argumentName, String value){
		
	}
	
	public int getNumArguments(){
		return argumentList.size();
	}
	
	public void parse(String s){
		
	}
	
	public ArgumentValues getArgument(){
		return null;
	}
}