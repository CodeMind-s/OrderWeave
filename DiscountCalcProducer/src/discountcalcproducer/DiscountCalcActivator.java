package discountcalcproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import ordergeneratorproducer.OrderService;

public class DiscountCalcActivator implements BundleActivator {

	private ServiceRegistration<?> registration;
    private ServiceReference<?> orderServiceRef;

    @Override
    public void start(BundleContext context) throws Exception{
        orderServiceRef = context.getServiceReference(OrderService.class.getName());
        OrderService orderService = (OrderService) context.getService(orderServiceRef);
        DiscountService service = new DiscountServiceImpl(orderService);
        registration = context.registerService(DiscountService.class.getName(), service, null);
        System.out.println("DiscountService registered");
        // Simulate discount calculation
        service.calculateDiscount("Order123");
    }

    @Override
    public void stop(BundleContext context) throws Exception{
        registration.unregister();
        context.ungetService(orderServiceRef);
        System.out.println("DiscountService unregistered");
    }

}
