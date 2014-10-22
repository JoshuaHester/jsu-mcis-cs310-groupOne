package edu.jsu.mcis;

public class TooManyArgumentsException extends RuntimeException {
	
	private String unexpectedArgument;
	private String usageInfo;
	
	public TooManyArgumentsException(String s, String a) {
			unexpectedArgument = s;
			usageInfo = a;
	}
	public String getUnexpectedArgument() {
		return unexpectedArgument;
	}

	public String toString() {
		return usageInfo + " Unrecognised arguments: " + unexpectedArgument;
	}
}
