package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class GroupOne{
	public static float CalVol(float l, float w, float h){
		return l*w*h;
	}
	
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
}