package discountcalcproducer;

import ordergeneratorproducer.OrderService;

public class DiscountServiceImpl implements DiscountService {

	private final OrderService orderService;

	public DiscountServiceImpl(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public double calculateDiscount(String orderId) {
		String details = orderService.getOrderDetails(orderId);
		System.out.println("Calculating discount for order: " + orderId + " based on " + details);
		double discount = 10.0; // Fixed discount for simplicity
		System.out.println("Discount calculated: " + discount + " for order: " + orderId);
		return discount;
	}

}
