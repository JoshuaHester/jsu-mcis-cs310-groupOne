package edu.jsu.mcis;

public class TooManyArguments extends RuntimeException {
	
	private String unexpectedArgument;
	
	public TooManyArguments(String s) {
			unexpectedArgument = s;
	}
	public String getInfo() {
		return unexpectedArgument;
	}
	public String toString() {
		return "Unrecognized arguments: " + unexpectedArgument;
	}
}

