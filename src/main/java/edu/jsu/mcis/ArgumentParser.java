package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser {

	private String programName ="";
	private List<String> positionalArgList;
	private List<String> optionalArgList;
	private Hashtable<String,Argument> argumentTable;
	private boolean helpFlagExits;
	
	public ArgumentParser(){
		positionalArgList = new ArrayList<String>(5);
		optionalArgList = new ArrayList<String>(5);
		argumentTable = new Hashtable<String,Argument>(5);
		setProgramName();
		helpFlagExits = true;
	}
	
	public void addArgument(String argumentName){
		argumentTable.put(argumentName, new Argument(argumentName));
		positionalArgList.add(argumentName);
	}
	
	
	public void addArgument(Argument.DataType type, String argumentName){
		argumentTable.put(argumentName, new Argument(type, argumentName));
		positionalArgList.add(argumentName);
	}

	public void addArgument(String argumentName, Argument.DataType type){
		addArgument(type, argumentName);
	}
	
	public void addOptionalArgument(Argument.DataType type, String argumentName){
		argumentTable.put(argumentName, new Argument(type, argumentName));
		optionalArgList.add(argumentName);
		if(type.toString().equals("boolean")){
			getArgument(argumentName).setValue("false");
		}
	}
	
	public void addOptionalArgument(String argumentName, Argument.DataType type){
		addOptionalArgument(type, argumentName);
	}
	
	public void addOptionalArgument(Argument.DataType type, String argumentName, String defaultVal){
		argumentTable.put(argumentName, new Argument(type, argumentName));
		optionalArgList.add(argumentName);
		getArgument(argumentName).setValue(defaultVal);
	}
	
	public void addOptionalArgument(String argumentName, Argument.DataType type, String defaultVal){
		addOptionalArgument(type, argumentName, defaultVal);
	}
	
	public int getNumPosArguments(){
		return positionalArgList.size();
	}
	
	public int getNumOptArguments(){
		return optionalArgList.size();
	}
	
	public int getNumArguments(){
		return getNumPosArguments()+getNumOptArguments();
	}
	
	public void setHelpFlagExits(boolean h) {
		helpFlagExits = h;
	}
	
	public void parse(String s){
		Scanner scan = new Scanner(s);
		String nextVal = "";
		boolean loop = true;
		int i = 0;
		boolean helpCalled = false;
		while(loop){
			if(scan.hasNext()){
				nextVal = scan.next();
				if(nextVal.equals("-h")||nextVal.equals("--help")){
					loop=false;
					System.out.println(getUsage());
					if(helpFlagExits) {
						System.exit(0);
					}
					else{
						helpCalled = true;
					}
				}
				else if(nextVal.contains("--")){
					String argName = nextVal.substring(2);
					if(getArgument(argName).getType().equals("boolean")){
						getArgument(argName).setValue("true");
					}
					else{
						getArgument(argName).setValue(scan.next());
					}
				}
				else if(getNumPosArguments()>i){
					try{
						argumentTable.get(positionalArgList.get(i)).setValue(nextVal);	
					}
					catch(Exception e){
						throw new InvalidDataTypeException(argumentTable.get(positionalArgList.get(i)),nextVal);
					}
				i++;
				}else {
					String a = nextVal;
					while(scan.hasNext()){
						a=a+" "+scan.next();
					}
						throw new TooManyArgumentsException(a);
				}
			}
			else loop = false;
		}
		if(i<getNumPosArguments()&&!helpCalled){
			String missingArg=argumentTable.get(positionalArgList.get(i)).getName();
			for(i=i+1;i<getNumPosArguments();i++){
				missingArg=missingArg+" "+argumentTable.get(positionalArgList.get(i)).getName();
			}
			throw new TooFewArgumentsException(missingArg);
		}
	}
	
	public Argument getArgument(String argName){
		if(argumentTable.containsKey(argName)){
			return argumentTable.get(argName);
		}
		else{	
			Argument val = new Argument("");
			val.setValue("");
			return val;
		}
	}
	
	public String getUsage(){
		String s = programName.toString()+"\n positional arguments:";
		for(int i=0;i<getNumPosArguments();i++){
			s=s+"\n "+argumentTable.get(positionalArgList.get(i)).getName();
			if(argumentTable.get(positionalArgList.get(i)).getDescription()!=null){
				s=s+" "+argumentTable.get(positionalArgList.get(i)).getDescription();
			}
		}
		if(getNumOptArguments() != 0){
			for(int i = 0;i < getNumOptArguments();i++){
				s = s+"\n --"+argumentTable.get(optionalArgList.get(i)).getName();
				if(argumentTable.get(optionalArgList.get(i)).getDescription() != null){
					s = s+" "+argumentTable.get(optionalArgList.get(i)).getDescription();
				}
			}
		}
		return s;
	}
	
	public void loadXML(String path){
		LoadXML loader = new LoadXML(path);
		positionalArgList=loader.getPosArgs();
		optionalArgList=loader.getPosArgs();
		argumentTable=loader.getArgs();
	}
	
	private void setProgramName(){
		programName = Thread.currentThread().getStackTrace()[3].getClassName();
	}
}
