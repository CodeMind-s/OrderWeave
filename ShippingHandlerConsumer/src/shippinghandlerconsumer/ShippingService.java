package shippinghandlerconsumer;

public interface ShippingService {
	void handleShipping(String orderId);
	void setOrderId(String orderId);
    String getOrderId();
}
