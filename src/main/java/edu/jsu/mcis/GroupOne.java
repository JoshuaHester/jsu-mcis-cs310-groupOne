package edu.jsu.mcis;

import java.util.*;
import java.util.Scanner;

public class GroupOne{

	public void main(String args[]){
		Scanner key = new Scanner(System.in);
		String input = "";
		while(!input.equals("QUIT")){
			input = key.nextLine().trim();
			ArgumentParser arg = new ArgumentParser();
			arg.addArgument(ArgumentValues.Types.INT, "length");
			arg.addArgument(ArgumentValues.Types.INT, "width");
			arg.addArgument(ArgumentValues.Types.INT, "height");
			arg.parse(input);
			if(arg.getHelpOut()){System.out.println(arg.getUsage());}
			else if(arg.checkTooManyArg()){System.out.println(arg.missingArguments());}
			else if(arg.checkTooFewArg()){System.out.println(arg.tooManyArguments());}
		}
	}
}