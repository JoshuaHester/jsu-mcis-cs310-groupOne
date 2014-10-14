package edu.jsu.mcis;
/* 
 * Create a class called ArgumentValues that can hold named,string values 
 * representing positional arguments.
 *
 */
import java.util.*;
//Date Created 9/30/2014

public class ArgumentValues<T>{

	private String argument;
	private T value;
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
	
	/* This object is used to hold positional arguments while associating
	 * values with it. Overall it should be created and stored in a 
	 * Vector<ArgumentValues> somewhere in the ArgumentParser.class so 
	 * it can be easily accessed later through sorting.
	 */
	 
	public String getValue(){
		return value;
	}
	public String getName(){
		return argument;
	}
	public void setValue(String v){
		switch(variables){
		case INT:
			break;
		case FLOAT:
			break;
		case BOOLEAN:
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
}