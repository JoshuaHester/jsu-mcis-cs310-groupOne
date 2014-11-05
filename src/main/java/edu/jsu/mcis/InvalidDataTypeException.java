package edu.jsu.mcis;

public class InvalidDataTypeException extends RuntimeException {
	private Argument arg;
	private String value;
	private String exceptionClassInfo;
	
	public InvalidDataTypeException(Argument arg, String value) {
			this.value= value;
			this.arg=arg;
			setExceptionClassName();
	}
	
	public String getValue() {
		return value;
	}
	
	public String getType(){
		return arg.getType();
	}
	
	public String getName(){
		return arg.getName();
	}
	
	public String toString() {
		String result= exceptionClassInfo + " argument " + arg.getName() + ": invalid " + arg.getType() + " value: " + value;
		return result;
	}
	
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
}
