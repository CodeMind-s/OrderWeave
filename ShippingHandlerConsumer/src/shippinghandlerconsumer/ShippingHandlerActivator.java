package shippinghandlerconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import inventorytrackerproducer.InventoryService;

public class ShippingHandlerActivator implements BundleActivator {

	private ServiceRegistration<?> registration;
    private ServiceReference<?> inventoryServiceRef;

    @Override
    public void start(BundleContext context) {
        inventoryServiceRef = context.getServiceReference(InventoryService.class.getName());
        InventoryService inventoryService = (InventoryService) context.getService(inventoryServiceRef);
        ShippingService service = new ShippingServiceImpl(inventoryService);
        registration = context.registerService(ShippingService.class.getName(), service, null);
        System.out.println("ShippingService registered");
        // Simulate shipping
        service.handleShipping("Order123");
    }

    @Override
    public void stop(BundleContext context) {
        registration.unregister();
        context.ungetService(inventoryServiceRef);
        System.out.println("ShippingService unregistered");
    }

}
