package edu.jsu.mcis;

public class InvalidArgumentException extends RuntimeException{
	//constructor a method that returns the value that is passed into the constructor
	private String argName;
	private String exceptionClassInfo;
	public InvalidArgumentException(String argName){
		this.argName = argName;
	}
	
	public String getValue(){
		return argName;
	}
	 
	public String toString(){
		String result = exceptionClassInfo+" The specified argument: "+argName+" does not exist";
		return result;
	}
	
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
}