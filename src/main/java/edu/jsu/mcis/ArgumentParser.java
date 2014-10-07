package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser{
	private String programName;
	private Vector<ArgumentValues> argumentList; 
	private boolean helpOut = false;
	private ArgumentValues invalidArg;
	
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
		boolean loop = true;
		int i=0;
		while(loop){
			if(scan.hasNext()){
				if(getNumArguments()<i+1){
					String a =scan.next();
					while(scan.hasNext()){
					a=a+" "+scan.next();
					}
					invalidArg = new ArgumentValues("invalid");
					invalidArg.setValue(a);
					loop = false;
				}
				else{
					if(helpOut == false){
						nextVal = scan.next();
						if(nextVal.equals("-h")){
							helpOut = true;
							loop = false;
						}
						else {
							argumentList.get(i).setValue(nextVal);
							i++;
						}
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
	
	public String getUnknownValue(){
		return invalidArg.getValue();
	}
	
	public String getUnknownArg(){
		return null;
	}
	
}