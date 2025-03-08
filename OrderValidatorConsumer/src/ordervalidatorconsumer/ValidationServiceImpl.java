package ordervalidatorconsumer;

import discountcalcproducer.DiscountService;
import ordergeneratorproducer.OrderService;

public class ValidationServiceImpl implements ValidationService {

	private final OrderService orderService;
    private final DiscountService discountService;

    public ValidationServiceImpl(OrderService orderService, DiscountService discountService) {
        this.orderService = orderService;
        this.discountService = discountService;
    }

    @Override
    public boolean validateOrder(String orderId) {
        System.out.println("Validating order: " + orderId);
        String details = orderService.getOrderDetails(orderId);
        double discount = discountService.calculateDiscount(orderId);
        boolean isValid = details != null && discount >= 0;
        System.out.println("Order " + orderId + " validation result: " + isValid);
        return isValid;
    }

}
