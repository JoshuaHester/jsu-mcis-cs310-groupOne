import edu.jsu.mcis.*;

public class ProductOwnerExample {
	public static float CalVol(float l, float w, float h, String type){
		if(type.equals("sphere")){
			return (4*3.14f*(l*l*l))/3;
		}
		else return l*w*h;
	}
	
	public static void main(String args[]){
		ArgumentParser arg = new ArgumentParser();
		arg.addArgument(ArgumentValues.Types.FLOAT, "length", "The length of the object. Radius if object is a sphere");
		arg.addArgument(ArgumentValues.Types.FLOAT, "width", "The width of the object");
		arg.addArgument(ArgumentValues.Types.FLOAT, "height", "The height of the object");
		arg.addOptionalArgument(ArgumentValues.Types.STRING, "type", "The type of object having its volume calculated");
		String input = "ProductOwnerExample ";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
		try {
			arg.parse(input);
		}
		catch(TooManyArguments e) {
			System.out.println("yup");
			System.out.println(e.getInfo() + " was the extra arg");
		}
		//if(arg.getHelpOut()){System.out.println(arg.getUsage());}
		//else{
			float l = arg.getArgument("length").getValue();
			float w = arg.getArgument("width").getValue();
			float h = arg.getArgument("height").getValue();
			String t = arg.getArgument("type").getValue();
			float v = CalVol(l,w,h,t);
			System.out.println(v);
		//}
	}
}
