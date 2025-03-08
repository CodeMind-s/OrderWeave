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
        paymentServiceRef = context.getServiceReference(PaymentService.class.getName());
        PaymentService paymentService = (PaymentService) context.getService(paymentServiceRef);
        InventoryService service = new InventoryServiceImpl(paymentService);
        registration = context.registerService(InventoryService.class.getName(), service, null);
        System.out.println("InventoryService registered");
        // Simulate inventory update
        service.updateInventory("Order123");
    }

    @Override
    public void stop(BundleContext context) {
        registration.unregister();
        context.ungetService(paymentServiceRef);
        System.out.println("InventoryService unregistered");
    }

}
