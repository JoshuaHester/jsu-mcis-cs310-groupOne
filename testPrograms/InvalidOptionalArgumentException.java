package edu.jsu.mcis;

public class InvalidOptionalArgumentException extends RunTimeException{

	private String invalidOptionalArgumentOption;
	private String exceptionClassInfo;
	
	public InvalidOptionalArgumentException(String s){
		invalidOptionalArgument = s;
		setExceptionClassName();
	}
	
	public String getInvalidOptionalArgument(){
		return invalidOptionalArgument;
	}
	
	private void setExceptionClassName(){
		exceptionClassInfp = this.getClass().getSimpleName();
	}
	
	public String toString(){
		return exceptionClassInfo + " not a valid optional argument: " + invalidOptionalArgument;
	}
}