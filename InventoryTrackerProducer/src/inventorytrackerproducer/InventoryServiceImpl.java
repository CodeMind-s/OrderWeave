package inventorytrackerproducer;

import paymentprocessconsumer.PaymentService;

public class InventoryServiceImpl implements InventoryService {

	private final PaymentService paymentService;

    public InventoryServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void updateInventory(String orderId) {
        System.out.println("Checking payment status for inventory update: " + orderId);
        boolean paymentProcessed = paymentService.processPayment(orderId);
        if (paymentProcessed) {
            System.out.println("Inventory updated for order: " + orderId);
        } else {
            System.out.println("Inventory update failed due to payment issue: " + orderId);
        }
    }
}
