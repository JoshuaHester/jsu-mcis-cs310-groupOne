package edu.jsu.mcis;

public class WrongDataType extends RuntimeException {
	
	private String info;
	
	public WrongDataType(String s) {
			info = s;
	}
	public String getInfo() {
		return info;
	}
	public String toString() {
		return "Incorrect data type: " + info;
	}
}

