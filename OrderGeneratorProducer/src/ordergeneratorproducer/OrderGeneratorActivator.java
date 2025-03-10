package ordergeneratorproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class OrderGeneratorActivator implements BundleActivator {

	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("\n=====================================");
		System.out.println("Starting Order Generator Bundle...");

		try {

			System.out.println("\nWelcome to ORDERWEAVE - Order Processing System");
			System.out.println("=====================================\n");
			System.out.println("Registering OrderService...");
			OrderService service = new OrderServiceImpl();
			registration = context.registerService(OrderService.class.getName(), service, null);
			System.out.println("OrderService registered successfully.");

			// Simulate generating an order
			System.out.println("\nGenerating a new order...");
			String orderId = service.generateOrder();
			service.setOrderId(orderId);
			Number amount = service.getAmount();
			int itemCount = service.getItemCount();

			System.out.println("Order generated successfully. Order ID: " + orderId);
			System.out.println("Total Amount to be Payed: " + amount);
			System.out.println("Number of items to be purchased: " + itemCount);

		} catch (Exception e) {
			System.out.println("An error occurred while starting the Order Generator Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("\n=====================================");
		System.out.println("Stopping Order Generator Bundle...");

		try {
			if (registration != null) {
				registration.unregister();
				System.out.println("OrderService unregistered successfully.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while stopping the Order Generator Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}
}
