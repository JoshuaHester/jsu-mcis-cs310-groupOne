package edu.jsu.mcis;

/*
Note: this class should either be renamed or merged with InvalidArgumentException, depending on its function
*/

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