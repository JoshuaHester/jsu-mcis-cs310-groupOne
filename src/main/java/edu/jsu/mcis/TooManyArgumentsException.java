package edu.jsu.mcis;

public class TooManyArgumentsException extends RuntimeException {
	
	private String unexpectedArgument;
	private String exceptionClassInfo;
	
	public TooManyArgumentsException(String s) {
			unexpectedArgument = s;
			setExceptionClassName();
	}
	public String getUnexpectedArgument() {
		return unexpectedArgument;
	}

	public String toString() {
		return exceptionClassInfo + " Unrecognised arguments: " + unexpectedArgument;
	}
		
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
}
