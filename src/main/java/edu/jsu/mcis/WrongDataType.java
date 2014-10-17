package edu.jsu.mcis;

public class WrongDataType extends RuntimeException {
	private ArgumentValues arg;
	
	private String value;
	
	public WrongDataType(ArgumentValues arg, String value) {
			this.value= value;
			this.arg=arg;
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
		String result= "argument " + arg.getType() + "invalid" + arg.getName() + "argument" + value;
		return result;
	}
}

