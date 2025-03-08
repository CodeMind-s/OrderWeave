package customernotifierconsumer;

public interface NotificationService {
	void notifyCustomer(String orderId, String message);
}
