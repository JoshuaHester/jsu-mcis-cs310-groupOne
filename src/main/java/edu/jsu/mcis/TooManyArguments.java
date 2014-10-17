package edu.jsu.mcis;

public class TooManyArguments extends RuntimeException {
	
	private String info;
	
	public TooManyArguments(String s) {
			info = s;
	}
	public String getInfo() {
		return info;
	}
	public String toString() {
		return "There are too many arguments " + info;
	}
}

