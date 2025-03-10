package paymentprocessconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import ordervalidatorconsumer.ValidationService;

public class PaymentProcessActivator implements BundleActivator {

	private ServiceRegistration<?> registration;
	private ServiceReference<?> validationServiceRef;

	@Override
	public void start(BundleContext context) {
		System.out.println("\n=====================================");
		System.out.println("Starting Payment Processor Bundle...");

		try {
			System.out.println("Fetching ValidationService reference...");
			validationServiceRef = context.getServiceReference(ValidationService.class.getName());
			ValidationService validationService = (ValidationService) context.getService(validationServiceRef);

			System.out.println("Registering PaymentService...");
			PaymentService service = new PaymentServiceImpl(validationService);
			registration = context.registerService(PaymentService.class.getName(), service, null);
			System.out.println("PaymentService registered successfully.");

			String orderId = validationService.getOrderId();
			service.setOrderId(orderId);
			
			int itemCount = validationService.getItemCount();
			service.setItemCount(itemCount);

			// Simulate payment processing
			System.out.println("\nProcessing payment for an order...\n");
			service.processPayment(orderId);
			System.out.println("\nPayment processing completed successfully.\n");

		} catch (Exception e) {
			System.out.println("An error occurred while starting the Payment Processor Bundle: " + e.getMessage() + "\n");
			System.out.println("PLEASE START ValidationService!");
//			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}

	@Override
	public void stop(BundleContext context) {
		System.out.println("\n=====================================");
		System.out.println("Stopping Payment Processor Bundle...");

		try {
			if (registration != null) {
				registration.unregister();
				System.out.println("PaymentService unregistered successfully.");
			}
			if (validationServiceRef != null) {
				context.ungetService(validationServiceRef);
				System.out.println("ValidationService reference released successfully.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while stopping the Payment Processor Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}
}