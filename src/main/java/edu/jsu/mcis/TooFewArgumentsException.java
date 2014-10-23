package edu.jsu.mcis;

public class TooFewArgumentsException extends RuntimeException {
	
	private String expectedArgument;
	private String exceptionClassInfo;
	
	public TooFewArgumentsException(String s) {
			expectedArgument= s;
			setExceptionClassName();
	}
	public String getMissingArgumentName() {
		return expectedArgument;
	}
	public String toString() {
		return exceptionClassInfo + " The following arguments are required: " + expectedArgument;
	}
	
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
}
