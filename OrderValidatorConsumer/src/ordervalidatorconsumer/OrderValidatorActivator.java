package ordervalidatorconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import discountcalcproducer.DiscountService;
import ordergeneratorproducer.OrderService;

public class OrderValidatorActivator implements BundleActivator {

	private ServiceRegistration<?> registration;
	private ServiceReference<?> orderServiceRef;
	private ServiceReference<?> discountServiceRef;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("\n=====================================");
		System.out.println("Starting Order Validator Bundle...");

		try {

			System.out.println("Fetching OrderService reference...");
			orderServiceRef = context.getServiceReference(OrderService.class.getName());
			OrderService orderService = (OrderService) context.getService(orderServiceRef);

			System.out.println("Fetching DiscountService reference...");
			discountServiceRef = context.getServiceReference(DiscountService.class.getName());
			DiscountService discountService = (DiscountService) context.getService(discountServiceRef);

			System.out.println("Registering ValidationService...");
			ValidationService service = new ValidationServiceImpl(orderService, discountService);
			registration = context.registerService(ValidationService.class.getName(), service, null);
			System.out.println("ValidationService registered successfully.");

			String orderId = orderService.getOrderId();
			service.setOrderId(orderId);

			
			int itemCount = orderService.getItemCount();
			service.setItemCount(itemCount);

			// Simulate order validation
			System.out.println("\nValidating an order...");
			service.validateOrder(orderId);
			Number Amount = orderService.getAmount();
			service.setAmount(Amount);
			
			System.out.println("Order validation completed successfully.");

		} catch (Exception e) {
			System.out.println("An error occurred while starting the Order Validator Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("\n=====================================");
		System.out.println("Stopping Order Validator Bundle...");

		try {
			if (registration != null) {
				registration.unregister();
				System.out.println("ValidationService unregistered successfully.");
			}
			if (orderServiceRef != null) {
				context.ungetService(orderServiceRef);
				System.out.println("OrderService reference released successfully.");
			}
			if (discountServiceRef != null) {
				context.ungetService(discountServiceRef);
				System.out.println("DiscountService reference released successfully.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while stopping the Order Validator Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}
}