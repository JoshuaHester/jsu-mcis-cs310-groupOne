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
			default:
				answer = normalTransaction(price, percentage);
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
		arg.addArgument(DataType.FLOAT, "price", "The price of your bill.");
		arg.addArgument(DataType.FLOAT, "percentage", "This is the tip percentage.");
		arg.addOptionalArgument(DataType.STRING, "coupon","none", "if the customer has a coupon.");
		
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