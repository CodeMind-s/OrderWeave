package inventorytrackerproducer;

import paymentprocessconsumer.PaymentService;

public class InventoryServiceImpl implements InventoryService {

	private final PaymentService paymentService;
	private String orderId;
	private 
	int availableItemCount = 100;
	int purchaseItemCount;

	@Override
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	public InventoryServiceImpl(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public void updateInventory(String orderId) {
		System.out.println("Checking payment status for inventory update: " + orderId);
		boolean paymentProcessed = paymentService.processPayment(orderId);
		int ItemCount = paymentService.getItemCount();
		
		if (paymentProcessed) {
			availableItemCount = availableItemCount- ItemCount;
			System.out.println("Inventory updated for order: " + orderId);
			System.out.println("Remaining Inventory Items: " + availableItemCount);
		} else {
			System.out.println("Inventory update failed due to payment issue: " + orderId);
		}
	}

	@Override
	public void setitemCount(int itemCount) {
		this.purchaseItemCount= itemCount;
	}

	@Override
	public int getitemCount() {
		// TODO Auto-generated method stub
		return purchaseItemCount;
	}
	

}
