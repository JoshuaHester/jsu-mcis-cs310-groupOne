import edu.jsu.mcis.*;

public class GroupOneKeywords{
	
	private ArgumentParser p;
	
	public void StartProgramWithArguments1(String s) {
		p = new ArgumentParser();
		p.addArgument("length");
		p.addArgument("width");
		p.addArgument("height");
		p.parse(s);
	}
	
	public String getLength(){
		String s =  p.getArgument("length").getValue();
		return s;
	}
	
	public String getWidth(){
		String s = p.getArgument("width").getValue();
		return s;
	}
	
	public String getHeight(){
		String s =  p.getArgument("height").getValue();
		return s;
	}
	
	public void StartProgramWithArguments2(String s) {
		p = new ArgumentParser();
		p.addArgument("pet");
		p.addArgument("number");
		p.addArgument("rainy");
		p.addArgument("bathrooms");
		p.parse(s);
	}
	
	public String getPet(){
		String s = p.getArgument("pet").getValue();
		return s;
	}
	
	public String getNumber(){
		String s =  p.getArgument("number").getValue();
		return s;
	}
	
	public String getRainy(){
		String s =  p.getArgument("rainy").getValue();
		return s;
	}
	
	public String getBathrooms(){
		String s =  p.getArgument("bathrooms").getValue();
		return s;
	}
	
	public void StartProgramWithOptionalArgument(String s) {
		p = new ArgumentParser();
		p.addArgument("length");
		p.addArgument("width");
		p.addArgument("height");
		p.addOptionalArgument("type", DataType.STRING);
		p.parse(s);
	}
	
	public String getType(){ 
		String s =  p.getArgument("type").getValue();
		return s;
	}
}
