import java.util.*;
import edu.jsu.mcis.*;

public class Palindrome{
	private boolean iC;
	private String input;
	
	public Palindrome(String pal, boolean ignoreCase){
		int length = pal.length();
		String reverse = "";
		input = pal;
		if(ignoreCase) pal = pal.toLowerCase();
		for ( int i = length - 1; i >= 0; i-- ){
			reverse = reverse + pal.charAt(i);
		}
		iC=pal.equals(reverse);
	}
	
	public String getResult(){
	if (iC)
        return input+" is a palindrome.";
    else
        return input+" is not a palindrome.";
	}
	
	
	
	public static void main(String args[]){
		ArgumentParser arg = new ArgumentParser();
		//arg.addArgument(DataType.STRING, "word", ": string without spaces");
		//arg.addOptionalArgument(DataType.BOOLEAN, "ignoreCase", "false",": ignores capitalization");
		argp.LoadXML(Palindrome.xml);
		String input = "";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
		arg.parse(input);
		String d = arg.getArgument("data").getValue();
		boolean i = arg.getArgument("ignoreCase").getValue();
		Palindrome p = new Palindrome(d,i);
		System.out.println(p.getResult());
	}
}
 
