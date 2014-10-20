package edu.jsu.mcis;

public class WrongDataType extends RuntimeException {
	private ArgumentValues arg;
	
	private String value;
	private String usageInfo;
	
	public WrongDataType(ArgumentValues arg, String value, String a) {
			this.value= value;
			this.arg=arg;
			usageInfo = a;
	}
	public String getValue() {
		return value;
	}
	public String getType(){
		return arg.getType();
	}
	
	public String getName(){
		return arg.getName();
	}
	
	public String toString() {
		String result= usageInfo + " argument " + arg.getName() + ": invalid " + arg.getType() + " value: " + value;
		return result;
	}
}

