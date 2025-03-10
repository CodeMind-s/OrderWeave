package inventorytrackerproducer;

public interface InventoryService {
	void updateInventory(String orderId);
	void setOrderId(String orderId);
    String getOrderId();
    
    void setitemCount(int itemCount);

	int getitemCount();
}
