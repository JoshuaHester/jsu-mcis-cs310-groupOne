package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser{
	private String programName;
	private boolean helpOut = false;
	private List<String> keyMapList;
	private Hashtable<String,ArgumentValues> argumentTable;
	
	
	
	public ArgumentParser(){
		keyMapList = new ArrayList<String>(5);
		argumentTable = new Hashtable<String,ArgumentValues>(5);
	}
	
	public void addArgument(String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(argumentName));
		keyMapList.add(argumentName);
	}
	
	public void addArgument(String argumentName, String argumentDescription){
		argumentTable.put(argumentName, new ArgumentValues(argumentName, argumentDescription));
		keyMapList.add(argumentName);
	}
	
	public void addArgument(ArgumentValues.Types type, String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName));
		keyMapList.add(argumentName);
	}
	
	public int getNumArguments(){
		return keyMapList.size();
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
					throw new TooManyArguments(a);
				}
				else{
					nextVal = scan.next();
					if(nextVal.equals("-h")){
						helpOut = true;
						loop = false;
					}
					else {
						argumentTable.get(keyMapList.get(i)).setValue(nextVal);
						i++;
					}
				}
			}
			else loop = false;
		}	
		if(i<getNumArguments()&&!helpOut){
			String missingArg=argumentTable.get(keyMapList.get(i)).getName();
			for(i=i+1;i<getNumArguments();i++){
				missingArg=missingArg+" "+argumentTable.get(keyMapList.get(i)).getName();
			}
			throw new TooFewArguments(missingArg);
		}
	}
	
	public boolean getHelpOut(){
		return helpOut;
	}
	
	public ArgumentValues getArgument(String argName){
		if(argumentTable.containsKey(argName)){
			return argumentTable.get(argName);
		}
		else{	
			ArgumentValues val =new ArgumentValues("");
			val.setValue("");
			return val;
		}
	}
	
	public String getUsage(){
		String s = programName.toString()+"\n positional arguments:";
		for(int i=0;i<getNumArguments();i++){
			s=s+"\n "+argumentTable.get(keyMapList.get(i)).getName();
			if(argumentTable.get(keyMapList.get(i)).getDescription()!=null){
				s=s+" "+argumentTable.get(keyMapList.get(i)).getDescription();
			}
		}
		return s;
	}
		
	public String getMessage(){
		return "";
	}
}