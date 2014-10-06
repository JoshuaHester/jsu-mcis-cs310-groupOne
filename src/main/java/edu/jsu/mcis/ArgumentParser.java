package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser{
	private String programName;
	private Vector<ArgumentValues> argumentList; 
	private boolean helpOut = false;
	
	
	public ArgumentParser(){
		argumentList = new Vector<ArgumentValues>();
	}
	
	public void addArgument(String argumentName){
		argumentList.add(new ArgumentValues(argumentName));
	}
	
	public void addArgument(String argumentName, String argumentDescription){
		argumentList.add(new ArgumentValues(argumentName, argumentDescription));
	}
	
	public int getNumArguments(){
		return argumentList.size();
	}
	
	public void parse(String s){
		Scanner scan = new Scanner(s);
		programName = scan.next();
		String nextVal = "";
//		for(int i=0;i<getNumArguments();i++){
		boolean loop = true;
		int i=0;
		while(loop){
			if(scan.hasNext()){
				if(helpOut == false){
					nextVal = scan.next();
					if(nextVal.equals("-h")){
						helpOut = true;
					}
					else {
						argumentList.get(i).setValue(nextVal);
						i++;
					}
				}
			}
			else loop = false;
		}
	}
	
	public boolean getHelpOut(){
		return helpOut;
	}
	
	public ArgumentValues getArgument(String argName){
		ArgumentValues argument;
		for(int i=0;i<getNumArguments();i++){
			argument = argumentList.get(i);
			if(argument.getName().equals(argName)){
				return argument;
			}
		}
		ArgumentValues val =new ArgumentValues("");
		val.setValue("");
		return val;
	}
	
	public String getUsage(){
		String s = programName.toString()+"/n positional arguments:";
		for(int i=0;i<getNumArguments();i++){
			s=s+"/n "+argumentList.get(i).getName();
			if(argumentList.get(i).getDescription()!=null){
				s=s+" "+argumentList.get(i).getDescription();
			}
		}
		return s;
	}
}