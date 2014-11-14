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
		arg.addArgument(Argument.DataType.FLOAT, "first");
		arg.getArgument("first").setDescription( "The first number in the equation");
		arg.addArgument(Argument.DataType.FLOAT, "second");
		arg.getArgument("second").setDescription( "The second number in the equation");
		arg.addArgument("third", Argument.DataType.FLOAT);
		arg.getArgument("third").setDescription("The third number in the equation");
		arg.addOptionalArgument(Argument.DataType.STRING, "type", "add");
		arg.getArgument("type").setDescription( "The type of arithmetic to perform on the equations: add(default), subtract, multiply");
		arg.getArgument("type").setShortName("t");
		//arg.saveToXML("myArguments.XML") 			functionality that is wanted from the product owner. May want to make a different class. BorderFactory that creates boarder. Class that builds argument parser
		String input = "";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
		arg.parse(input);
		float f = arg.getArgument("first").getValue();
		float s = arg.getArgument("second").getValue();
		float th = arg.getArgument("third").getValue();
		String t = arg.getArgument("type").getValue();
		Equations cal = new Equations(f,s,th,t);
		System.out.println(cal.getResult());
	}
}