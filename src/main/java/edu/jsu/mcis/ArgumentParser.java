package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser {

	private String programName ="";
	private boolean helpFlag = false;
	private List<String> positionalArgList;
	private List<String> optionalArgList;
	private Hashtable<String,ArgumentValues> argumentTable;
	
	public ArgumentParser(){
		positionalArgList = new ArrayList<String>(5);
		optionalArgList = new ArrayList<String>(5);
		argumentTable = new Hashtable<String,ArgumentValues>(5);
		setProgramName();
	}
	
	public void addArgument(String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(argumentName));
		positionalArgList.add(argumentName);
	}
	
	public void addArgument(String argumentName, String argumentDescription){
		argumentTable.put(argumentName, new ArgumentValues(argumentName, argumentDescription));
		positionalArgList.add(argumentName);
	}
	
	public void addArgument(DataType type, String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName));
		positionalArgList.add(argumentName);
	}
	public void addArgument(String argumentName, DataType type){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName));
		positionalArgList.add(argumentName);
	}
	
	public void addArgument(DataType type, String argumentName, String argumentDescription){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName, argumentDescription));
		positionalArgList.add(argumentName);
	}
	
	
	public void addOptionalArgument(DataType type, String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName));
		optionalArgList.add(argumentName);
	}
	
	public void addOptionalArgument(DataType type, String argumentName, String desc){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName, desc));
		optionalArgList.add(argumentName);
	}
	
	public int getNumArguments(){
		return positionalArgList.size();
	}
	
	public int getNumOptArguments(){
		return optionalArgList.size();
	}
	
	public void parse(String s){
		Scanner scan = new Scanner(s);
		String nextVal = "";
		boolean loop = true;
		int i=0;
		while(loop){
			if(scan.hasNext()){
				nextVal = scan.next();
				if(getNumArguments()<i+1&&!nextVal.contains("-")){
					String a =nextVal;
					while(scan.hasNext()){
						a=a+" "+scan.next();
					}
					throw new TooManyArgumentsException(a);
				}
				else{
					if(nextVal.equals("-h")||nextVal.equals("--help")){
						loop=false;
						helpFlag=true;
						System.out.println(getUsage());
						
//						System.exit(0);
					}
					else if(nextVal.contains("--")){
						String argName = nextVal.substring(2);
						getArgument(argName).setValue(scan.next());
					}
					else {
						try{
							argumentTable.get(positionalArgList.get(i)).setValue(nextVal);
						}
						catch(Exception e){
							throw new InvalidDataTypeException(argumentTable.get(positionalArgList.get(i)),nextVal);
						}
						i++;
					}
				}
			}
			else loop = false;
		}	
		if(i<getNumArguments()&&!helpFlag){
			String missingArg=argumentTable.get(positionalArgList.get(i)).getName();
			for(i=i+1;i<getNumArguments();i++){
				missingArg=missingArg+" "+argumentTable.get(positionalArgList.get(i)).getName();
			}
			throw new TooFewArgumentsException(missingArg);
		}
	}
	
	public boolean isHelpCalled(){
		return helpFlag;
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
			s=s+"\n "+argumentTable.get(positionalArgList.get(i)).getName();
			if(argumentTable.get(positionalArgList.get(i)).getDescription()!=null){
				s=s+" "+argumentTable.get(positionalArgList.get(i)).getDescription();
			}
		}
		if(getNumOptArguments()!=0){
			for(int i=0;i<getNumOptArguments();i++){
				s=s+"\n --"+argumentTable.get(optionalArgList.get(i)).getName();
				if(argumentTable.get(optionalArgList.get(i)).getDescription()!=null){
					s=s+" "+argumentTable.get(optionalArgList.get(i)).getDescription();
				}
			}
		}
		return s;
	}
	
	private void setProgramName(){
		programName = Thread.currentThread().getStackTrace()[3].getClassName();
	}
}
