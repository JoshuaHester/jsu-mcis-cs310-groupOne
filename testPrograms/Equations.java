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
			default:
				result = add(first, second, third);
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
		arg.addArgument(DataType.FLOAT, "first", "The first number in the equation");
		arg.addArgument(DataType.FLOAT, "second", "The second number in the equation");
		arg.addArgument("third", DataType.FLOAT, "The third number in the equation");
		arg.addOptionalArgument(DataType.STRING, "type", "add", "The type of arithmetic to perform on the equations");
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