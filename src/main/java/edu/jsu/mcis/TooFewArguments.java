package edu.jsu.mcis;

public class TooFewArguments extends RuntimeException {
	
	private String info;
	
	public TooFewArguments(String s) {
			info = s;
	}
	public String getInfo() {
		return info;
	}
	public String toString() {
		return "There are too few arguments " + info;
	}

}