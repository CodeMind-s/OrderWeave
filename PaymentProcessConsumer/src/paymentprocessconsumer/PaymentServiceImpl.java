package paymentprocessconsumer;

import ordervalidatorconsumer.ValidationService;

public class PaymentServiceImpl implements PaymentService {

	private final ValidationService validationService;

	public PaymentServiceImpl(ValidationService validationService) {
		this.validationService = validationService;
	}

	@Override
	public boolean processPayment(String orderId) {
		System.out.println("Processing payment for order: " + orderId);
		boolean isValid = validationService.validateOrder(orderId);
		if (isValid) {
			System.out.println("Payment processed successfully for order: " + orderId);
			return true;
		} else {
			System.out.println("Payment failed due to invalid order: " + orderId);
			return false;
		}
	}

}
