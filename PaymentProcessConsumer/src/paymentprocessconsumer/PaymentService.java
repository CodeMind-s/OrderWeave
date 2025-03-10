package paymentprocessconsumer;

public interface PaymentService {
	boolean processPayment(String orderId);
	void setOrderId(String orderId);
    String getOrderId();
    void setItemCount(int itemcount);

	int getItemCount();
}
