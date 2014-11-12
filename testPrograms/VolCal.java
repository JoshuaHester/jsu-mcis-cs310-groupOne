import edu.jsu.mcis.*;


public class VolCal {
	private float result;
	
	public VolCal(float l, float w, float h, String type){
		switch(type){
			case "sphere":
				result = calcSphere(l);
				break;
			case "pyramid":
				result = calcPyramid(l,w,h);
				break;
			default:
				result = calcBox(l,w,h);
		}
	}
	
	public float calcBox(float l, float w, float h){
		return l*w*h;
	}
	
	public float calcSphere(float r){
		return (4*3.14f*(r*r*r))/3;
	}
	
	public float calcPyramid(float l, float w, float h){
		return (l*w*h)/3;
	}
	
	public float getResult(){
		return result;
	}
	
	
	public static void main(String args[]){
		ArgumentParser arg = new ArgumentParser();
		arg.addArgument(Argument.DataType.FLOAT, "length");
		arg.getArgument("length").setDescription("The length of the object. Radius if object is a sphere");
		arg.addArgument(Argument.DataType.FLOAT, "width");
		arg.getArgument("width").setDescription("The width of the object");
		arg.addArgument("height", Argument.DataType.FLOAT);
		arg.getArgument("height").setDescription("The height of the object");
		arg.addOptionalArgument(Argument.DataType.STRING, "type", "box");
		arg.getArgument("type").setDescription("The type of object having its volume calculated");
		arg.getArgument("type").setShortName("t");
		//arg.addFlag("hollow");
		//arg.getArgument("hollow").setDescription("whether the object is hollow");
		String input = "";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
		arg.parse(input);
		float l = arg.getArgument("length").getValue();
		float w = arg.getArgument("width").getValue();
		float h = arg.getArgument("height").getValue();
		String t = arg.getArgument("type").getValue();
		VolCal cal = new VolCal(l,w,h,t);
		System.out.println(cal.getResult());
	}
}
