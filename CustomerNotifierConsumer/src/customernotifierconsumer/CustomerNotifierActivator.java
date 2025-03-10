package customernotifierconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import emailserviceproducer.EmailService;
import shippinghandlerconsumer.ShippingService;

public class CustomerNotifierActivator implements BundleActivator {

	private ServiceRegistration<?> registration;
	private ServiceReference<?> emailServiceRef;
	private ServiceReference<?> shippingServiceRef;

	@Override
	public void start(BundleContext context) {
		System.out.println("\n=====================================");
		System.out.println("Starting Customer Notifier Bundle...");

		try {
			System.out.println("\nLooking up EmailService...");
			emailServiceRef = context.getServiceReference(EmailService.class.getName());
			EmailService emailService = (EmailService) context.getService(emailServiceRef);

			System.out.println("Looking up ShippingService...");
			shippingServiceRef = context.getServiceReference(ShippingService.class.getName());
			ShippingService shippingService = (ShippingService) context.getService(shippingServiceRef);

			System.out.println("Initializing NotificationService...");
			NotificationService service = new NotificationServiceImpl(emailService, shippingService);
			registration = context.registerService(NotificationService.class.getName(), service, null);
			System.out.println("NotificationService registered successfully.");

			String orderId = shippingService.getOrderId();
			// Simulate notification
			System.out.println("\nSending a customer notification...");
			service.notifyCustomer(orderId, "Your order has been shipped!");
			System.out.println("Customer notification sent successfully for Order ID: "+ orderId);

		} catch (Exception e) {
			System.out.println("An error occurred while starting the Customer Notifier Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}

	@Override
	public void stop(BundleContext context) {
		System.out.println("\n=====================================");
		System.out.println("Stopping Customer Notifier Bundle...");

		try {
			if (registration != null) {
				registration.unregister();
				System.out.println("NotificationService unregistered successfully.");
			}
			if (emailServiceRef != null) {
				context.ungetService(emailServiceRef);
				System.out.println("Released EmailService reference.");
			}
			if (shippingServiceRef != null) {
				context.ungetService(shippingServiceRef);
				System.out.println("Released ShippingService reference.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while stopping the Customer Notifier Bundle: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("=====================================\n");
	}
}
