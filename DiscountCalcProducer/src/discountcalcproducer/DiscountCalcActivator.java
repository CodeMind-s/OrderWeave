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
    public void start(BundleContext context) throws Exception {
        System.out.println("\n=====================================");
        System.out.println("Starting Discount Calculator Bundle...");

        try {
        	System.out.println();
            System.out.println("Fetching OrderService reference...");
            orderServiceRef = context.getServiceReference(OrderService.class.getName());
            OrderService orderService = (OrderService) context.getService(orderServiceRef);
         
            System.out.println("Fetching order ID and Amount from OrderService...");
            String orderId = orderService.getOrderId();
            Number amount = orderService.getAmount();


            System.out.println("Registering DiscountService...");
            DiscountService service = new DiscountServiceImpl(orderService);
            registration = context.registerService(DiscountService.class.getName(), service, null);
            System.out.println("DiscountService registered successfully.");

            // Simulate discount calculation
            System.out.println("\nCalculating discount for an order...");
            service.calculateDiscount(orderId,amount);
            System.out.println("Discount calculation completed successfully.");

        } catch (Exception e) {
            System.out.println("An error occurred while starting the Discount Calculator Bundle: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=====================================\n");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("\n=====================================");
        System.out.println("Stopping Discount Calculator Bundle...");

        try {
            if (registration != null) {
                registration.unregister();
                System.out.println("DiscountService unregistered successfully.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while stopping the Discount Calculator Bundle: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=====================================\n");
    }
}