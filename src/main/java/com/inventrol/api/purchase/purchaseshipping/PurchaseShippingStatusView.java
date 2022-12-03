package com.inventrol.api.purchase.purchaseshipping;

public interface PurchaseShippingStatusView {
	long getId();
	PurchaseShippingData getPurchaseshipping();
	interface PurchaseShippingData{
		String getStatus();
	}
}
