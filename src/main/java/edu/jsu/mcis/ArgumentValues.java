package edu.jsu.mcis;
// Create a class called ArgumentValues that can hold 
// named, string values representing positional arguments 

// Date Created 9/30/2014
// Authored by : Jarred & Sarah

public class ArgumentValues {

	public String programName;
	public String length;
	public String width;
	public String height;
	
	public ArgumentValues(){
		programName = "";
		length = "";
		width = "";
		height = "";
	}
	
	/* Change these methods to void so they do
	 * not return any values but rather set them
	*/
	public String setProgramName(String n){
		return programName = n;
	}
	public String setLength(String l){
		return length = l;
	}
	public String setWidth(String w){
		return width = w;
	}
	public String setHeight(String h){
		return height = h;
	}
	

	public static void main(String[] args){
	
	}
}