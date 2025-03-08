package shippinghandlerconsumer;

import inventorytrackerproducer.InventoryService;

public class ShippingServiceImpl implements ShippingService {

	private final InventoryService inventoryService;

	public ShippingServiceImpl(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@Override
	public void handleShipping(String orderId) {
		System.out.println("Preparing shipping for order: " + orderId);
		inventoryService.updateInventory(orderId);
		System.out.println("Shipping handled for order: " + orderId);
	}

}
