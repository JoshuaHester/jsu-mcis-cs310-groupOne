package edu.jsu.mcis;

import java.util.Scanner;

public class ArgumentParser{
	private String ProgramName;
	private String Length;
	private String Width;
	private String Height;

	public ArgumentParser(String parameters){
		ArgumentValues values = new ArgumentValues();
		Scanner text = new Scanner(parameters);
		
		ProgramName = text.next();
		Length = text.next();
		Width = text.next();
		Height = text.next();
		
		values.setProgramName(ProgramName);
		values.setLength(Length);
		values.setWidth(Width);
		values.setHeight(Height);
		
	}
	
	public String getProgramName(){
		return ProgramName;
	}
	
	public String getLength(){
		return Length;
	}
	
	public String getWidth(){
		return Width;
	}
	public String getHeight(){
		return Height;
	}
}