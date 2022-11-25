package com.inventrol.api.purchase.purchaseshipping;

import java.time.LocalDateTime;

public interface PurchaseShippingView {
	long getId();
	PurchaseData getPurchase();
	String getService();
	String getTrackingNumber();
	String getStatus();
	String getNotice();
	boolean isDeleted();
	LocalDateTime getUpdatedOn();
	interface PurchaseData {
		 long getId();
		 boolean isDeleted();
	}
}
