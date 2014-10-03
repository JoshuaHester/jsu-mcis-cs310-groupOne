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
			argumentList.get(i).setValue(scan.next());
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
/*	
	public String getUsage(){
		String s = programName.toString()+"/n positional arguments:/n ";
		
		s=s+argumentList.get(0).getName()+"/n ";
		s=s+argumentList.get(1).getName()+"/n ";
		s=s+argumentList.get(2).getName()+"/n ";
		
		
		
		
		System.out.println(s);
		return s;
	}
*/
	
	public String getUsage(){
		String s = programName.toString()+"/n positional arguments:/n ";
		for(int i=0;i<getNumArguments();i++){
			s=s+argumentList.get(i).getName()+"/n ";
		}
		return s;
	}
}