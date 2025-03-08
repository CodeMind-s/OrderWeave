package ordergeneratorproducer;

public class OrderServiceImpl implements OrderService {

	@Override
	public String generateOrder() {
		String orderId = "Order" + (int) (Math.random() * 1000);
		System.out.println("Generated order: " + orderId);
		return orderId;
	}

	@Override
	public String getOrderDetails(String orderId) {
		String details = "Details of " + orderId + ": Standard Order";
		System.out.println("Retrieved details: " + details);
		return details;
	}

}
