import java.util.*;
import edu.jsu.mcis.*;

public class Palindrome{
	private boolean isPalindrome = false;
	private String input;
	
	public Palindrome(String pal, boolean ignoreCase){
		int length = pal.length();
		
		input = pal;
		String reverse = "";
		
		if(ignoreCase) 
			pal = pal.toLowerCase();
			
		for ( int i = length - 1; i >= 0; i-- ){
			reverse = reverse + pal.charAt(i);
		}
		
		if (reverse.equals(pal))
			isPalindrome = true;

	}
	
	public String getResult(){
	if (isPalindrome)
        return input + " is a palindrome.";
    else 
        return input + " is not a palindrome.";
	}
	
	
	
	public static void main(String args[]){
		ArgumentParser arg = new ArgumentParser();
		arg.addArgument(Argument.DataType.STRING, "word");
		arg.getArgument("word").setDescription(": string without spaces");
		arg.addFlag("ignoreCase");
		arg.getArgument("ignoreCase").setDescription(": ignores capitalization");
		arg.getArgument("ignoreCase").setShortName("ic");
		
		String userInput = "";
		for(int i = 0; i < args.length; i++) {
			userInput += args[i] + " ";
		}
		arg.parse(userInput);
		String d = arg.getArgument("word").getValue();
		boolean i = arg.getArgument("ignoreCase").getValue();
		Palindrome p = new Palindrome(d,i);
		System.out.println(p.getResult());
	}
}
 
