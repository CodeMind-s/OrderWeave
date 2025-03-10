package shippinghandlerconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import inventorytrackerproducer.InventoryService;

public class ShippingHandlerActivator implements BundleActivator {

	private ServiceRegistration<?> registration;
	private ServiceReference<?> inventoryServiceRef;

	@Override
	public void start(BundleContext context) {
		System.out.println("\n=====================================");
		System.out.println("Starting Shipping Handler Bundle...");

		try {
			System.out.println("\nLooking up InventoryService...");
			inventoryServiceRef = context.getServiceReference(InventoryService.class.getName());
			InventoryService inventoryService = (InventoryService) context.getService(inventoryServiceRef);

			System.out.println("Initializing ShippingService...");
			ShippingService service = new ShippingServiceImpl(inventoryService);
			registration = context.registerService(ShippingService.class.getName(), service, null);
			System.out.println("ShippingService registered successfully.");
			
			String orderId = inventoryService.getOrderId();
			service.setOrderId(orderId);
			
			// Simulate shipping
			System.out.println("\nHandling shipping for a sample order...");
			service.handleShipping(orderId);
			System.out.println("Shipping process initiated for Order ID: " + orderId);

		} catch (Exception e) {
			System.out.println("An error occurred while starting the Shipping Handler Bundle: " + e.getMessage() + "\n");
			System.out.println("PLEASE START InventoryService!");
//			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}

	@Override
	public void stop(BundleContext context) {
		System.out.println("\n=====================================");
		System.out.println("Stopping Shipping Handler Bundle...");

		try {
			if (registration != null) {
				registration.unregister();
				System.out.println("ShippingService unregistered successfully.");
			}
			if (inventoryServiceRef != null) {
				context.ungetService(inventoryServiceRef);
				System.out.println("Released InventoryService reference.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while stopping the Shipping Handler Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}
}
