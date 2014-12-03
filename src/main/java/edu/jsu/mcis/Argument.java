package edu.jsu.mcis;

import java.util.*;

public class Argument {

	private String name;
	private Object value;
	private String description;
	private DataType variables = DataType.STRING;
	private String shortName;
	private List<Object> restrictedValue;
	
	
	public enum DataType{STRING, INT, FLOAT, BOOLEAN;
		
		public String toString(){
			switch(this){
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

	Argument(String argument){
		name = argument;
		value = "";
		variables = DataType.STRING;
		restrictedValue = new ArrayList<Object>();
	}
	
 	Argument(DataType t, String argument){
		this(argument);
		variables = t;
	}

	public <T> T getValue(){
		return (T) value;
	}

	
	public String getName(){
		return name;
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
	
	public String getShortName(){
		return shortName;
	}
	
	public void setShortName(String shortName){
		this.shortName = shortName;
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
	
	public void setRestrictedValue(String  arg){
		
			switch(variables){
			case INT:
				restrictedValue.add(Integer.parseInt(arg));
				break;
			case FLOAT:
				restrictedValue.add(Float.parseFloat(arg));
				break;
			default:
				restrictedValue.add(arg);
			}
		
	}
	public List getRestrictedValue()
	{
		return restrictedValue;
	}
}
