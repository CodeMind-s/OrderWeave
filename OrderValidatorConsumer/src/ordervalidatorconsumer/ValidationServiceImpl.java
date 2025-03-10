package ordervalidatorconsumer;

import discountcalcproducer.DiscountService;
import ordergeneratorproducer.OrderService;

public class ValidationServiceImpl implements ValidationService {

	private final OrderService orderService;
    private final DiscountService discountService;
    private String orderId;
    private Number amount;
    private double discount;
    private int itemCount;


    @Override
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    public ValidationServiceImpl(OrderService orderService, DiscountService discountService) {
        this.orderService = orderService;
        this.discountService = discountService;
    }

    @Override
    public boolean validateOrder(String orderId) {
        System.out.println("Validating order: " + orderId);
        String details = orderService.getOrderDetails(orderId);
        Number amount = orderService.getAmount();
       this.discount = discountService.calculateDiscount(orderId,amount);
        boolean isValid = details != null && discount >= 0;
        System.out.println("Order " + orderId + " validation result: " + isValid);
        return isValid;
    }

    @Override
	public void setAmount(Number amount) {
		this.amount = (amount.doubleValue() - discount);
	}
	@Override
	public Number getAmount() {
		return amount;
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
