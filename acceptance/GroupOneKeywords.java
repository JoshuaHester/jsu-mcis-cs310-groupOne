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
		return p.getArgument("length").getValue();
	}
	
	public String getWidth(){
		return p.getArgument("width").getValue();
	}
	
	public String getHeight(){
		return p.getArgument("height").getValue();
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
		return p.getArgument("pet").getValue();
	}
	
	public String getNumber(){
		return p.getArgument("number").getValue();
	}
	
	public String getRainy(){
		return p.getArgument("rainy").getValue();
	}
	
	public String getBathrooms(){
		return p.getArgument("bathrooms").getValue();
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
		return p.getArgument("type").getValue(); 
	}
}
