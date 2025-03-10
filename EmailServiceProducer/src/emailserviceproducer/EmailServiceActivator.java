package emailserviceproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class EmailServiceActivator implements BundleActivator {

    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) {
        System.out.println("\n=====================================");
        System.out.println("Starting Email Service Bundle...");

        try {
            System.out.println("\nInitializing EmailService...");
            EmailService service = new EmailServiceImpl();
            registration = context.registerService(EmailService.class.getName(), service, null);
            System.out.println("EmailService registered successfully.");

            // Simulate sending a test email
            System.out.println("\nReady to notify customers through email.");
//            service.sendEmail("test@example.com", "Test", "Email service started");
//            System.out.println("Test email sent successfully to test@example.com");

        } catch (Exception e) {
            System.out.println("An error occurred while starting the Email Service Bundle: " + e.getMessage());
//            e.printStackTrace();
        }

        System.out.println("=====================================\n");
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("\n=====================================");
        System.out.println("Stopping Email Service Bundle...");

        try {
            if (registration != null) {
                registration.unregister();
                System.out.println("EmailService unregistered successfully.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while stopping the Email Service Bundle: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=====================================\n");
    }
}
