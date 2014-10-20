package edu.jsu.mcis;

public class TooFewArguments extends RuntimeException {
	
	private String expectedArgument;
	private String usageInfo;
	
	public TooFewArguments(String s, String a) {
			expectedArgument= s;
			usageInfo = a;
	}
	public String getInfo() {
		return expectedArgument;
	}
	public String toString() {
		return usageInfo + " The following arguments are required: " + expectedArgument;
	}

}