package edu.jsu.mcis;

import java.util.*;

@SuppressWarnings("unchecked")
public class ArgumentValues{
	private String argument;
	private Object value;
	private String description;
	private Types variables = Types.STRING;
	public enum Types{STRING, INT, FLOAT, BOOLEAN};

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
	
	public ArgumentValues(Types t, String arg, String desc){
		this(t,arg);
		setDescription(desc);
	}

	public <T extends Object> T getValue(){
		return (T) value;
	}
	
	public String getName(){
		return argument;
	}
	
	public void setValue(String v){
		switch(variables){
		case INT:
			try{
				value = Integer.parseInt(v);
			}
			catch(Exception e){
				throw new WrongDataType(this,v);
			}
			break;
		case FLOAT:
			try{
				value = Float.parseFloat(v);
			}
			catch(Exception e){
				throw new WrongDataType(this,v);
			}
			
			break;
		case BOOLEAN:
			if(v.equals("true") || v.equals("false")){
				value = Boolean.parseBoolean(v);
			}
			else{
				throw new WrongDataType(this,v);
			}
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
	
	public String getType(){
		switch(variables){
		case INT:
			return "int";
		case FLOAT:
			return "float";
		case BOOLEAN:
			return "boolean";
		default:
			return "String";
		}
	}
}