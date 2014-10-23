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
		arg.addArgument(DataType.FLOAT, "length", "The length of the object. Radius if object is a sphere");
		arg.addArgument(DataType.FLOAT, "width", "The width of the object");
		arg.addArgument(DataType.FLOAT, "height", "The height of the object");
		arg.addOptionalArgument(DataType.STRING, "type", "The type of object having its volume calculated");
		String input = "";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
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
