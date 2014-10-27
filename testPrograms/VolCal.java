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
			default:
				result = calcBox(l,w,h);
		}
	}
	
	public float calcBox(float l, float w, float h){
		return 1*w*h;
	}
	
	public float calcSphere(float r){
		return (4*3.14f*(r*r*r))/3;
	}
	
	public float calcPyramid(float l, float w, float h){
		return 1*w*h;
	}
	
	public float getResult(){
		return result;
	}
	
	
	public static void main(String args[]){
		ArgumentParser arg = new ArgumentParser();
		arg.addArgument(DataType.FLOAT, "length", "The length of the object. Radius if object is a sphere");
		arg.addArgument(DataType.FLOAT, "width", "The width of the object");
		arg.addArgument("height", DataType.FLOAT, "The height of the object");
		arg.addOptionalArgument(DataType.STRING, "type", "box", "The type of object having its volume calculated");
		String input = "";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
		arg.parse(input);
		float l = arg.getArgument("length").getValue();
		float w = arg.getArgument("width").getValue();
		float h = arg.getArgument("height").getValue();
		String t = arg.getArgument("type").getValue();
		boolean tp = arg.getArgument("type").getIsPresent();
		VolCal cal = new VolCal(l,w,h,t);
		System.out.println(cal.getResult());
		if(!tp){System.out.println("Remember to specify the type if you want anything other than a box!!!");}
	}
}
