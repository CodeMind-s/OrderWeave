package inventorytrackerproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import paymentprocessconsumer.PaymentService;

public class InventoryTrackerActivator implements BundleActivator {

    private ServiceRegistration<?> registration;
    private ServiceReference<?> paymentServiceRef;

    @Override
    public void start(BundleContext context) {
        System.out.println("\n=====================================");
        System.out.println("Starting Inventory Tracker Bundle...");

        try {
            System.out.println("\nLooking up PaymentService...");
            paymentServiceRef = context.getServiceReference(PaymentService.class.getName());
            PaymentService paymentService = (PaymentService) context.getService(paymentServiceRef);

            System.out.println("Initializing InventoryService...");
            InventoryService service = new InventoryServiceImpl(paymentService);
            registration = context.registerService(InventoryService.class.getName(), service, null);
            System.out.println("InventoryService registered successfully.");

            String orderId = paymentService.getOrderId();
            service.setOrderId(orderId);
            
            // Simulate inventory update
            System.out.println("\nUpdating inventory for the order...");
            service.updateInventory(orderId);
            System.out.println("Inventory updated successfully for Order ID: "+orderId);

        } catch (Exception e) {
            System.out.println("An error occurred while starting the Inventory Tracker Bundle: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=====================================\n");
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("\n=====================================");
        System.out.println("Stopping Inventory Tracker Bundle...");

        try {
            if (registration != null) {
                registration.unregister();
                System.out.println("InventoryService unregistered successfully.");
            }
            if (paymentServiceRef != null) {
                context.ungetService(paymentServiceRef);
                System.out.println("Released PaymentService reference.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while stopping the Inventory Tracker Bundle: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=====================================\n");
    }
}
