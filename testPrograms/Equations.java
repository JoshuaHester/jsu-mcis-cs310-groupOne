import edu.jsu.mcis.*;


public class Equations {
	private float result;
	
	public Equations(float first, float second, float third, String type){
		switch(type){
			case "subtract":
				result = subtract(first, second, third);
				break;
			case "multiply":
				result = multiply(first, second, third);
				break;
			case "add":
				result = add(first, second, third);
				break;
			default:
				throw new InvalidOptionalArgumentException(type);
			}	
	}
	
	public float subtract(float first, float second, float third){
		return first - second - third;
	}
	
	public float multiply(float first, float second, float third){
		return (first * second * third);
	}
	
	public float add(float first, float second, float third){
		return (first + second + third);
	}
	
	public float getResult(){
		return result;
	}
	
	
	public static void main(String args[]){
		ArgumentParser arg = new ArgumentParser();
		arg.setProgramName("Equations");
		arg.setProgramDescription("Calculates a simple mathematical equation using three numeric values");
		arg.addArgument(Argument.DataType.FLOAT, "first");
		arg.getArgument("first").setDescription( "The first number in the equation");
		arg.addArgument(Argument.DataType.FLOAT, "second");
		arg.getArgument("second").setDescription( "The second number in the equation");
		arg.addArgument("third", Argument.DataType.FLOAT);
		arg.getArgument("third").setDescription("The third number in the equation");
		arg.addOptionalArgument(Argument.DataType.STRING, "type", "add");
		arg.getArgument("type").setDescription( "The type of arithmetic to perform on the equations: add(default), subtract, multiply");
		arg.getArgument("type").setShortName("t");
		arg.setRestrictedValue("first", "1","3");
		arg.setRestrictedValue("second", "5","7");
		arg.setRestrictedValue("third", "2","4");
		
		String input = "";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
		arg.parse(input);
		float f = arg.getArgument("first").getValue();
		float s = arg.getArgument("second").getValue();
		float th = arg.getArgument("third").getValue();
		String t = arg.getArgument("type").getValue();
		boolean restrictedF = arg.checkRestrictedValues("first", f);
		boolean restrictedS = arg.checkRestrictedValues("second", s);
		boolean restrictedT = arg.checkRestrictedValues("third", th);
		boolean foundRestricted = false;
		Equations cal = new Equations(f,s,th,t);
		if(restrictedF){
			System.out.println("Restricted Value" + f);
			foundRestricted = true;
		}
		if(restrictedS){
			System.out.println("Restricted Value" + s);
			foundRestricted = true;
		}
		if(restrictedT){
			System.out.println("Restricted Value" + th);
			foundRestricted = true;
		}
		
		if (foundRestricted == false){
				System.out.println(cal.getResult());
		}		
	}
}