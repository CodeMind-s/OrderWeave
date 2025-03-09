package ordervalidatorconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import discountcalcproducer.DiscountService;
import ordergeneratorproducer.OrderService;

public class OrderValidatorActivator implements BundleActivator {

	private ServiceRegistration<?> registration;
    private ServiceReference<?> orderServiceRef;
    private ServiceReference<?> discountServiceRef;

    @Override
    public void start(BundleContext context) throws Exception{
        orderServiceRef = context.getServiceReference(OrderService.class.getName());
        OrderService orderService = (OrderService) context.getService(orderServiceRef);
        
        discountServiceRef = context.getServiceReference(DiscountService.class.getName());
        DiscountService discountService = (DiscountService) context.getService(discountServiceRef);
        
        ValidationService service = new ValidationServiceImpl(orderService, discountService);
        registration = context.registerService(ValidationService.class.getName(), service, null);
        
        System.out.println("ValidationService registered");
        // Simulate validation
        service.validateOrder("Order123");
    }

    @Override
    public void stop(BundleContext context)throws Exception {
        registration.unregister();
        context.ungetService(orderServiceRef);
        context.ungetService(discountServiceRef);
        System.out.println("ValidationService unregistered");
    }

}
