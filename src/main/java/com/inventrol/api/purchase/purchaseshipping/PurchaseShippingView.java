package com.inventrol.api.purchase.purchaseshipping;

import java.time.LocalDateTime;

public interface PurchaseShippingView {
	 long getId();
	 SupplierData getSupplier();
	 PurchaseShippingData getPurchaseshipping();
	 LocalDateTime getCreatedOn();
	 boolean isDeleted();
	 interface SupplierData {
		 long getId();
		 String getName();
	 }
	 interface PurchaseShippingData{
		 long getId();
		 String getService();
			String getTrackingNumber();
			String getStatus();
			String getNotice();
			boolean isDeleted();
			LocalDateTime getUpdatedOn();
	 }
}
