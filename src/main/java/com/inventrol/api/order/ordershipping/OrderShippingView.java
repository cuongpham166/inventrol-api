package com.inventrol.api.order.ordershipping;

import java.time.LocalDateTime;

public interface OrderShippingView {
	long getId();
	boolean isDeleted();
	OrderShippingData getShipping();
	CustomerData getCustomer();
	
	interface CustomerData {
		long getId();
		String getName();
		boolean isDeleted();
	}
	
	interface OrderShippingData{
		long getId();
		String getStatus();
		String getService();
		String getTrackingNumber();
		String getNotice();
		LocalDateTime getUpdatedOn();
		boolean isDeleted();
	}
}
