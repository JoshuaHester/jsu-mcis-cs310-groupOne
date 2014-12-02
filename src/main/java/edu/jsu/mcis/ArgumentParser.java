package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class ArgumentParser {

	private String programName ="";
	protected List<String> positionalArgList;
	protected List<String> optionalArgList;
	protected Hashtable<String,Argument> argumentTable;
	private boolean helpFlagExits;
	private boolean addedFlag = false;
	private int [] restrictedIntValues;
	private float [] restrictedFloatValues;
	private String [] restrictedStringValues;
	
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
	
	public void addFlag(String argumentName){
		argumentTable.put(argumentName, new Argument(Argument.DataType.BOOLEAN, argumentName));
		optionalArgList.add(argumentName);
		getArgument(argumentName).setValue("false");
	}
	
	public boolean getAddedFlag(){
		return addedFlag;
	}
	
	public void setRestrictedValues(int [] argumentArray){
		restrictedIntValues = new int [argumentArray.length];
		
		for (int i = 0; i < argumentArray.length; i ++){
			restrictedIntValues[i] = argumentArray[i];
		}
		
	}
	
	public boolean checkRestrictedValues(int a){
		boolean isRestricted = false;
		
		for(int i = 0; i < restrictedIntValues.length; i ++){
			if(a == restrictedIntValues[i]){
				isRestricted = true;
				break;
			}
		}
		
		return isRestricted;
	}
	
	public void setRestrictedValues(float [] argumentArray){
		restrictedFloatValues = new float [argumentArray.length];
		
		for (int i = 0; i < argumentArray.length; i ++){
			restrictedFloatValues[i] = argumentArray[i];
		}
		
	}
	
	public boolean checkRestrictedValues(float a){
		boolean isRestricted = false;
		
		for(int i = 0; i < restrictedFloatValues.length; i ++){
			if(a == restrictedFloatValues[i]){
				isRestricted = true;
				break;
			}
		}
		
		return isRestricted;
	}
	
	public void setRestrictedValues(String [] argumentArray){
		restrictedStringValues = new String [argumentArray.length];
		
		for (int i = 0; i < argumentArray.length; i ++){
			restrictedStringValues[i] = argumentArray[i];
		}
		
	}
	
	public boolean checkRestrictedValues(String a){
		boolean isRestricted = false;
		
		for(int i = 0; i < restrictedStringValues.length; i ++){
			if(a == restrictedStringValues[i]){
				isRestricted = true;
				break;
			}
		}
		
		return isRestricted;
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
				else if(nextVal.contains("-")){
					String shortName = nextVal.substring(1);
					if(getArgumentByShortName(shortName).getType().equals("boolean")){
						getArgumentByShortName(shortName).setValue("true");
					}
					else{
						getArgumentByShortName(shortName).setValue(scan.next());
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
			throw new InvalidArgumentException(argName);
		}
	}
	
	public Argument getArgumentByShortName(String shortName){
		for(int i = 0; i < getNumOptArguments(); i ++){
			if(shortName.equals(getArgument(optionalArgList.get(i)).getShortName()))
				return getArgument(optionalArgList.get(i));
		}
		throw new InvalidArgumentException(shortName);
	}
	
	String getUsage(){
		String positionalArguments = "";
		for(int i=0;i<getNumPosArguments();i++){
			positionalArguments=positionalArguments+" " + argumentTable.get(positionalArgList.get(i)).getName();
		}
		
		String s = programName.toString() + positionalArguments + "\n";
		
		for(int i=0;i<getNumPosArguments();i++){
			s=s+"\n "+argumentTable.get(positionalArgList.get(i)).getName()+": ";
			if(argumentTable.get(positionalArgList.get(i)).getDescription()!=null){
				s=s+" "+argumentTable.get(positionalArgList.get(i)).getDescription()+".";
			}
		}
		if(getNumOptArguments() != 0){
			for(int i = 0;i < getNumOptArguments();i++){
				s = s+"\n --"+argumentTable.get(optionalArgList.get(i)).getName()+": ";
				if(argumentTable.get(optionalArgList.get(i)).getDescription() != null){
					s = s+" "+argumentTable.get(optionalArgList.get(i)).getDescription() +".";
				}
			}
		}
		return s;
	}
	
	public void loadXML(String path){
		LoadXML loader = new LoadXML(path);
		positionalArgList=loader.getPosArgs();
		optionalArgList=loader.getOptArgs();
		argumentTable=loader.getArgs();
	}
	
	private void setProgramName(){
		programName = Thread.currentThread().getStackTrace()[3].getClassName();
	}
}
