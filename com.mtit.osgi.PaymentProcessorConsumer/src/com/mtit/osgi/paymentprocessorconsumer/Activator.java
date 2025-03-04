package com.mtit.osgi.paymentprocessorconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.mtit.osgi.ordergeneratorproducer.OrderGenerate;

public class Activator implements BundleActivator {

	
	private ServiceRegistration<?> registration;
    private ServiceReference<?> validationServiceRef;

    @Override
    public void start(BundleContext context) {
//        validationServiceRef = context.getServiceReference(ValidationService.class.getName());
//        
//        ValidationService validationService = (ValidationService) context.getService(validationServiceRef);
//        PaymentProcessor service = new PaymentProcessorImpl(validationService);
//        registration = context.registerService(PaymentProcessor.class.getName(), service, null);
//        
        System.out.println("PaymentService registered");
        // Simulate payment processing
//        service.processPayment("Order123");
    }

    @Override
    public void stop(BundleContext context) {
        registration.unregister();
//        context.ungetService(validationServiceRef);
        System.out.println("PaymentService unregistered");
    }
}
