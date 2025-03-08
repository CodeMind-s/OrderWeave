package emailserviceproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class EmailServiceActivator implements BundleActivator {

	private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) {
        EmailService service = new EmailServiceImpl();
        registration = context.registerService(EmailService.class.getName(), service, null);
        System.out.println("EmailService registered");
        // Simulate sending a test email
        service.sendEmail("test@example.com", "Test", "Email service started");
    }

    @Override
    public void stop(BundleContext context) {
        registration.unregister();
        System.out.println("EmailService unregistered");
    }

}
