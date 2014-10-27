package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser {

	private String programName ="";
	private boolean helpOut = false;
	private List<String> keyMapList;
	private List<String> optionalList;
	private Hashtable<String,ArgumentValues> argumentTable;
	
	public ArgumentParser(){
		keyMapList = new ArrayList<String>(5);
		optionalList = new ArrayList<String>(5);
		argumentTable = new Hashtable<String,ArgumentValues>(5);
		setProgramName();
	}
	
	public void addArgument(String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(argumentName));
		keyMapList.add(argumentName);
	}
	
	public void addArgument(String argumentName, String argumentDescription){
		argumentTable.put(argumentName, new ArgumentValues(argumentName, argumentDescription));
		keyMapList.add(argumentName);
	}
	
	public void addArgument(DataType type, String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName));
		keyMapList.add(argumentName);
	}
	
	public void addArgument(DataType type, String argumentName, String argumentDescription){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName, argumentDescription));
		keyMapList.add(argumentName);
	}
	
	public void addOptionalArgument(DataType type, String argumentName){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName));
		optionalList.add(argumentName);
	}
	
	public void addOptionalArgument(DataType type, String argumentName, String desc){
		argumentTable.put(argumentName, new ArgumentValues(type, argumentName, desc));
		optionalList.add(argumentName);
	}
	
	public int getNumArguments(){
		return keyMapList.size();
	}
	
	public int getNumOptArguments(){
		return optionalList.size();
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
						helpOut=true;
						System.out.println(getUsage());
						
//						System.exit(0);
					}
					else if(nextVal.contains("--")){
						String argName = nextVal.substring(2);
						getArgument(argName).setValue(scan.next());
					}
					else {
						try{
							argumentTable.get(keyMapList.get(i)).setValue(nextVal);
						}
						catch(Exception e){
							throw new InvalidDataTypeException(argumentTable.get(keyMapList.get(i)),nextVal);
						}
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
			throw new TooFewArgumentsException(missingArg);
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
		if(getNumOptArguments()!=0){
			for(int i=0;i<getNumOptArguments();i++){
				s=s+"\n --"+argumentTable.get(optionalList.get(i)).getName();
				if(argumentTable.get(optionalList.get(i)).getDescription()!=null){
					s=s+" "+argumentTable.get(optionalList.get(i)).getDescription();
				}
			}
		}
		return s;
	}
	
	private void setProgramName(){
		programName = Thread.currentThread().getStackTrace()[3].getClassName();
	}
}
