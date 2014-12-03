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
			case "box":
				result = calcBox(l,w,h);
				break;
			default:
				throw new InvalidOptionalArgumentException(type);
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
		arg.setProgramName("VolCal");
		arg.addArgument(Argument.DataType.FLOAT, "length");
		arg.getArgument("length").setDescription("The length of the object. Radius if object is a sphere");
		arg.addArgument(Argument.DataType.FLOAT, "width");
		arg.getArgument("width").setDescription("The width of the object");
		arg.addArgument("height", Argument.DataType.FLOAT);
		arg.getArgument("height").setDescription("The height of the object");
		arg.addRequiredArgument("type",Argument.DataType.STRING, "box");
		arg.getArgument("type").setDescription("The type of object having its volume calculated:box(default), sphere, pyramid");
		//arg.setDescription("type", "The type of the object")
		arg.getArgument("type").setShortName("t");
		arg.addFlag("hollow");
		arg.getArgument("hollow").setDescription("whether the object is hollow");
		float [] restrictedValues = {1.1f,3.3f,5.5f,7.7f,9.9f};
		arg.setRestrictedValues(restrictedValues);

		
		String input = "";
		for(int i = 0; i < args.length; i++) {
			input += args[i] + " ";
		}
		arg.parse(input);
		float l = arg.getArgument("length").getValue();
		float w = arg.getArgument("width").getValue();
		float h = arg.getArgument("height").getValue();
		String t = arg.getArgument("type").getValue();
		boolean f = arg.getArgument("hollow").getValue();
		boolean restrictedL = arg.checkRestrictedValues(l);
		boolean restrictedW = arg.checkRestrictedValues(w);
		boolean restrictedH = arg.checkRestrictedValues(h);
		boolean foundRestricted = false;
		VolCal cal = new VolCal(l,w,h,t);
		if(restrictedL){
			System.out.println("Restricted Value" + l);
			foundRestricted = true;
		}
		if(restrictedW){
			System.out.println("Restricted Value" + w);
			foundRestricted = true;
		}
		if(restrictedH){
			System.out.println("Restricted Value" + h);
			foundRestricted = true;
		}
		
		if (foundRestricted == false){
			if(f){
				System.out.println(cal.getResult() + "\noptional flag is " + f);
			}
			else
				System.out.println(cal.getResult());
		}
	}
}
