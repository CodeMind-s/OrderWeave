package ordergeneratorproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class OrderGeneratorActivator implements BundleActivator {

	private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) {
        OrderService service = new OrderServiceImpl();
        registration = context.registerService(OrderService.class.getName(), service, null);
        System.out.println("OrderService registered");
        // Simulate generating an order
        String orderId = service.generateOrder();
    }

    @Override
    public void stop(BundleContext context) {
        registration.unregister();
        System.out.println("OrderService unregistered");
    }

}
