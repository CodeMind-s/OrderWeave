package customernotifierconsumer;

import emailserviceproducer.EmailService;
import shippinghandlerconsumer.ShippingService;

public class NotificationServiceImpl implements NotificationService {

	private final EmailService emailService;
    private final ShippingService shippingService;

    public NotificationServiceImpl(EmailService emailService, ShippingService shippingService) {
        this.emailService = emailService;
        this.shippingService = shippingService;
    }

    @Override
    public void notifyCustomer(String orderId, String message) {
        System.out.println("Notifying customer for order: " + orderId);
        shippingService.handleShipping(orderId); // Ensure shipping is done
        String to = "customer@example.com";
        String subject = "Order Update: " + orderId;
        String body = "Your order: " + orderId + "\n" + message;
        emailService.sendEmail(to, subject, body);
        System.out.println("\nCustomer notified for order: " + orderId);
    }

}
