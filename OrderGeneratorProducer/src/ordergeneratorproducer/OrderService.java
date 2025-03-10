package ordergeneratorproducer;

public interface OrderService {
	String generateOrder();
	Number generateAmount();
	String getOrderDetails(String orderId);
	void setOrderId(String orderId);
    String getOrderId();
    void setAmount(Number amount);
    Number getAmount();
	int generateItemCount();
    void setItemCount(int itemCount);
    int getItemCount();
    
}
