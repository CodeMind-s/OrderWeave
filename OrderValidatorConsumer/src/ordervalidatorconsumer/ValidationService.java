package ordervalidatorconsumer;

public interface ValidationService {
	boolean validateOrder(String orderId);

	void setOrderId(String orderId);

	String getOrderId();

	void setAmount(Number amount);

	Number getAmount();
	void setItemCount(int itemCount);

	int getItemCount();
}
