package com.mtit.osgi.ordergeneratorproducer;

import java.util.List;

public interface OrderGenerate {
	public String generateOrder();
	public String getOrderDetails(String orderId);
	
}
