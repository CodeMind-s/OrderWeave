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
        emailServiceRef = context.getServiceReference(EmailService.class.getName());
        EmailService emailService = (EmailService) context.getService(emailServiceRef);
        shippingServiceRef = context.getServiceReference(ShippingService.class.getName());
        ShippingService shippingService = (ShippingService) context.getService(shippingServiceRef);
        NotificationService service = new NotificationServiceImpl(emailService, shippingService);
        registration = context.registerService(NotificationService.class.getName(), service, null);
        System.out.println("NotificationService registered");
        // Simulate notification
        service.notifyCustomer("Order123", "Your order has been shipped!");
    }

    @Override
    public void stop(BundleContext context) {
        registration.unregister();
        context.ungetService(emailServiceRef);
        context.ungetService(shippingServiceRef);
        System.out.println("NotificationService unregistered");
    }

}
