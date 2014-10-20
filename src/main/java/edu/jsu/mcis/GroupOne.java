package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class GroupOne{
	public static float CalVol(float l, float w, float h, String type){
		if(type.equals("sphere")){
			return (4*3.14f*(l*l*l))/3;
		}
		else return l*w*h;
	}
	
	public static void main(String args[]){
		Scanner key=new Scanner(System.in);
		String input = "";
		while(!input.equals("QUIT")){
			input = key.nextLine().trim();
			ArgumentParser arg = new ArgumentParser();
			arg.addArgument(ArgumentValues.Types.FLOAT, "length", "The length of the object. Radius if object is a sphere");
			arg.addArgument(ArgumentValues.Types.FLOAT, "width", "The width of the object");
			arg.addArgument(ArgumentValues.Types.FLOAT, "height", "The height of the object");
			arg.addOptionalArgument(ArgumentValues.Types.STRING, "type", "The type of object having its volume calculated");
			arg.parse(input);
			if(arg.getHelpOut()){System.out.println(arg.getUsage());}
			else{
				float l = arg.getArgument("length").getValue();
				float w = arg.getArgument("width").getValue();
				float h = arg.getArgument("height").getValue();
				String t = arg.getArgument("type").getValue();
				float v = CalVol(l,w,h,t);
				System.out.println(v);
			}
		}
	}
	
/*	
	public static void main(String args[]){
		Scanner key = new Scanner(System.in);
		String input = "";
		while(!input.equals("QUIT")){
			input = key.nextLine().trim();
			ArgumentParser arg = new ArgumentParser();
			arg.addArgument(ArgumentValues.Types.FLOAT, "length");
			arg.addArgument(ArgumentValues.Types.FLOAT, "width");
			arg.addArgument(ArgumentValues.Types.FLOAT, "height");
			arg.parse(input);
			if(arg.getHelpOut()){System.out.println(arg.getUsage());}
			else if(arg.checkTooManyArg()){System.out.println(arg.tooManyArguments());}
			else if(arg.checkTooFewArg()){System.out.println(arg.missingArguments());}
			else{
				float l = arg.getArgument("length").getValue();
				float w = arg.getArgument("width").getValue();
				float h = arg.getArgument("height").getValue();
				float v = CalVol(l,w,h);
				System.out.println(v);
			}
		}
	}
*/
}