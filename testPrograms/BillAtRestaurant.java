import edu.jsu.mcis.*;
 
public class BillAtRestaurant {
	private float answer;
	
	public BillAtRestaurant(float price, float percentage, String coupon){
		switch(coupon){
			case "halfoff":
				answer = halfOffTransaction(price, percentage);
				break;
			case "$5off":
				answer = fiveOffTranscation(price, percentage);
				break;
			case("none"):
				answer = normalTransaction(price, percentage);
				break;
			default:
				throw new InvalidOptionalArgumentException(coupon);
		}
	}
	
	public float normalTransaction(float price, float percentage){
		float tip = percentage/100;
		return Math.round((tip*price + price)*100)/100f;
	}
	
	public float halfOffTransaction(float price, float percentage){
		float tip = percentage/100;
		float bill = price/2;
		return Math.round((tip*bill + bill)*100)/100f;
	}
	
	public float fiveOffTranscation(float price, float percentage){
		float tip = percentage/100;
		float bill = price - 5;
		return Math.round((tip*bill + bill)*100)/100f;
	}
	
	public float getAnswer() {
		return answer;
	}
	
	public static void main(String[] args) {
		ArgumentParser arg = new ArgumentParser();
		arg.setProgramName("BillAtRestaurant");
		arg.addArgument(Argument.DataType.FLOAT, "price");
		arg.getArgument("price").setDescription( "The price of your bill.");
		arg.addArgument(Argument.DataType.FLOAT, "percentage");
		arg.getArgument("percentage").setDescription("This is the tip percentage.");
		arg.addRequiredArgument("coupon",Argument.DataType.STRING, "none");
		arg.getArgument("coupon").setDescription("if the customer has a coupon: none(default), halfoff, $5off");
		arg.getArgument("coupon").setShortName("c");
		
		
		String input = "";
		for(int i = 0; i < args.length; i++){
			input += args[i] + " ";
		}
		
		arg.parse(input);
		float x = arg.getArgument("price").getValue();
		float y = arg.getArgument("percentage").getValue();
		String z = arg.getArgument("coupon").getValue();
		
		BillAtRestaurant total = new BillAtRestaurant(x, y, z);
		System.out.println("Total price is: $" + total.getAnswer());
	}

}