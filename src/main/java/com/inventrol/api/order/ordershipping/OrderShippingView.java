package com.inventrol.api.order.ordershipping;

import java.time.LocalDateTime;

public interface OrderShippingView {
	long getId();
	OrderData getOrder();
	String getStatus();
	String getService();
	String getTrackingNumber();
	String getNotice();
	LocalDateTime getUpdatedOn();
	 boolean isDeleted();
	interface OrderData {
		 long getId();
		 boolean isDeleted();
	}
}
