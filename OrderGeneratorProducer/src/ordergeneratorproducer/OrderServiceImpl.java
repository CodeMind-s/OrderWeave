package ordergeneratorproducer;

public class OrderServiceImpl implements OrderService {
	private String orderId;
	private Number amount;
	private int purchaseItemCount;

	@Override
	public String generateOrder() {
		String orderId = "Order " + (int) (Math.random() * 1000);
		
		System.out.println("Generated order: " + orderId);
		setItemCount(generateItemCount());
		this.amount=generateAmount();
		
		return orderId;
	}
	@Override
	public int generateItemCount() {
		this.purchaseItemCount = 1 + (int) (Math.random() * 9); 
		
		System.out.println("No of Items Purchased: " + purchaseItemCount);
		
		return purchaseItemCount;
	}
	public Number generateAmount() {
		Number amount = (int)(1000+(Math.random()*1000*purchaseItemCount));
		System.out.println("Generated Total Amount without Discount: "+amount);
		return amount;
	}

	@Override
	public String getOrderDetails(String orderId) {
		String details = "Details of " + orderId + " has Total Amount to be payed as "+amount;
		System.out.println("Retrieved details: " + details);
		return details;
	}

	@Override
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}
	@Override
	public void setAmount(Number amount) {
		this.amount = amount;
	}
	@Override
	public Number getAmount() {
		return amount;
	}
	@Override
	public void setItemCount(int itemCount) {
		this.purchaseItemCount = itemCount;
	}
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return purchaseItemCount;
	}
}
