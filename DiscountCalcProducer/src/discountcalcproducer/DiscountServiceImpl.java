package discountcalcproducer;

import ordergeneratorproducer.OrderService;

public class DiscountServiceImpl implements DiscountService {

	private final OrderService orderService;

	public DiscountServiceImpl(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public double calculateDiscount(String orderId,Number Amount) {
		String details = orderService.getOrderDetails(orderId);
		System.out.println("Calculating discount for order: " + orderId + " based on Order Details ");
		double discount = Amount.doubleValue() * (10.0 / 100.0);
		System.out.println("Discount calculated: " + discount + " for order: " + orderId);
		return discount;
	}

}
