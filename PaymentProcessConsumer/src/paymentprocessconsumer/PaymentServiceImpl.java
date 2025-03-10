package paymentprocessconsumer;

import ordervalidatorconsumer.ValidationService;

public class PaymentServiceImpl implements PaymentService {

	private final ValidationService validationService;
	private String orderId;
	private int itemCount;

	@Override
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	public PaymentServiceImpl(ValidationService validationService) {
		this.validationService = validationService;
	}

	@Override
	public boolean processPayment(String orderId) {

		Number finalPayment = validationService.getAmount();
		System.out.println("Payment to be done after discount:" + finalPayment);
		System.out.println("Processing payment for order: " + orderId + " with final amount: " + finalPayment);
		boolean isValid = validationService.validateOrder(orderId);
		if (isValid) {
			System.out.println("Payment processed successfully for order: " + orderId + " Payment: " + finalPayment);
			return true;
		} else {
			System.out.println("Payment failed due to invalid order: " + orderId);
			return false;
		}
	}

	@Override
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return itemCount;
	}

}
