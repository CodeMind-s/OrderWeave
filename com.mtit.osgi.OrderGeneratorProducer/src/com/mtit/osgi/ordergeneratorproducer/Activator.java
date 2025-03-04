package com.mtit.osgi.ordergeneratorproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) {
    	OrderGenerate service = new OrderGenerateImpl();
        registration = context.registerService(OrderGenerate.class.getName(), service, null);
        System.out.println("OrderGeneration registered");
        
        // Simulate generating an order
        String orderId = service.generateOrder();
    }

    @Override
    public void stop(BundleContext context) {
        registration.unregister();
        System.out.println("OrderGeneration unregistered");
    }

}
