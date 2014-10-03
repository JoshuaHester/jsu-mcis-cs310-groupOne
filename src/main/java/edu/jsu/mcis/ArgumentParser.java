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
	
	public int getNumArguments(){
		return argumentList.size();
	}
	
	public void parse(String s){
		Scanner scan = new Scanner(s);
		programName = scan.next();
		for(int i=0;i<getNumArguments();i++){
			argumentList.get(i).addValue(scan.next());
		}
	}
	
	public ArgumentValues getArgument(String argName){
	ArgumentValues argument;
		for(int i=0;i<getNumArguments();i++){
			argument = argumentList.get(i);
			if(argument.getName().equals(argName)){
				return argument;
			}
		}
		//return null;
		ArgumentValues bob =new ArgumentValues("");
		bob.setValue("");
		return bob;
	}
}