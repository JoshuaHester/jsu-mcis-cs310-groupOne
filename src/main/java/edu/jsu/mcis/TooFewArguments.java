package edu.jsu.mcis;

public class TooFewArguments extends RuntimeException {
	
	private String expectedArgument;
	
	public TooFewArguments(String s) {
			expectedArgument= s;
	}
	public String getInfo() {
		return expectedArgument;
	}
	public String toString() {
		return "The following arguments are required: " + expectedArgument;
	}

}