package edu.jsu.mcis;

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
	