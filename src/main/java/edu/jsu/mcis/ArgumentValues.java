package edu.jsu.mcis;

import java.util.*;

@SuppressWarnings("unchecked")
public class ArgumentValues {

	private String name;
	private Object value;
	private String description;
	private DataType variables = DataType.STRING;

	public ArgumentValues(String arg){
		name = arg;
		value = "";
	}
	
	public ArgumentValues(String arg, String desc){
		this(arg);
		setDescription(desc);
	}
	
	public ArgumentValues(DataType t, String arg){
		this(arg);
		variables = t;
	}
	
	public ArgumentValues(DataType t, String arg, String desc){
		this(t,arg);
		setDescription(desc);
	}

	public <T> T getValue(){
		return (T) value;
	}
	
	public String getName(){
		return name;
	}
	
	public void setValue(String v){
		switch(variables){
		case INT:
			value = Integer.parseInt(v);
			break;
		case FLOAT:
			value = Float.parseFloat(v);
			break;
		case BOOLEAN:
			if(v.equals("true") || v.equals("false")){
				value = Boolean.parseBoolean(v);
			}
			else{
				throw new RuntimeException();
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
	return variables.toString();
	}
}
