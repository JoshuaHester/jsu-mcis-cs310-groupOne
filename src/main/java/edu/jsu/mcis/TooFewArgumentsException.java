package edu.jsu.mcis;

public class TooFewArgumentsException extends RuntimeException {
	
	private String expectedArgument;
	private String usageInfo;
	
	public TooFewArgumentsException(String s, String a) {
			expectedArgument= s;
			usageInfo = a;
	}
	public String getMissingArgumentName() {
		return expectedArgument;
	}
	public String toString() {
		return usageInfo + " The following arguments are required: " + expectedArgument;
	}

}