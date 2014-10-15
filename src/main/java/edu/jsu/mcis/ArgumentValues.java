package edu.jsu.mcis;

import java.util.*;

@SuppressWarnings("unchecked")
public class ArgumentValues{
	private String argument;
	private Object value;
	private String description;
	private Types variables = Types.STRING;
	public enum Types{STRING, INT, FLOAT, BOOLEAN};
	private boolean correctType = true;
	private String varType = "String";
	
	public ArgumentValues(String arg){
		argument = arg;
		value = "";
	}
	
	public ArgumentValues(String arg, String desc){
		this(arg);
		setDescription(desc);
	}
	
	public ArgumentValues(Types t, String arg){
		this(arg);
		variables = t;
	}

	public <T extends Object> T getValue(){
		switch(variables){
		case INT:
			return (T) Integer.valueOf(value.toString());
		case FLOAT:
			return (T) Float.valueOf(value.toString());
		case BOOLEAN:
			return (T) Boolean.valueOf(value.toString());
		default:
			return (T) value.toString();
		}
	}
	
	public String getName(){
		return argument;
	}
	
	public void setValue(String v){
		switch(variables){
		case INT:
			varType = "int";
			try{
				value = Integer.parseInt(v);
			}
			catch(Exception e){correctType = false;}
			break;
		case FLOAT:
			varType = "float";
			try{
				value = Float.parseFloat(v);
			}
			catch(Exception e){correctType = false;}
			
			break;
		case BOOLEAN:
			varType = "boolean";
			if(v.equals("true") || v.equals("false")){
				value = Boolean.parseBoolean(v);
			}
			else
				correctType = false;
			break;
		default:
			value = v;
		}
	}
	
	public void setDescription(String s){
		description = s;
	}

	public String getDescription(){
		return description;
	}
	
	public boolean isExpectedType(){
		return correctType;
	}
	
	public String expectedType(){
		return varType;
	}
}