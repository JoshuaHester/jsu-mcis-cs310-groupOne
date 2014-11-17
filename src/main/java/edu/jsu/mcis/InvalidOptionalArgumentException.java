package edu.jsu.mcis;

public class InvalidOptionalArgumentException extends RuntimeException{

	private String invalidOptionalArgument;
	private String exceptionClassInfo;
	
	public InvalidOptionalArgumentException(String s){
		invalidOptionalArgument = s;
		setExceptionClassName();
	}
	
	public String getInvalidOptionalArgument(){
		return invalidOptionalArgument;
	}
	
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
	
	public String toString(){
		return exceptionClassInfo + " not a valid optional argument: " + invalidOptionalArgument;
	}
}