package ordergeneratorproducer;

public interface OrderService {
	String generateOrder();

	String getOrderDetails(String orderId);
}
