package com.inventrol.api.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


public interface PurchaseView {
	long getId();
	Set<PurchaseItemData> getPurchaseItem();
	SupplierData getSupplier();
	PurchaseShippingData getPurchaseshipping();
	int getNumberOfItems();
	BigDecimal getTotal();
	String getNotice();
	String getPaymentType();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	boolean isDeleted();
	
	interface PurchaseShippingData{
		long getId();
		String getStatus();
		String getTrackingNumber();
		String getService();
	}
	interface PurchaseItemData {
		long getId();
		int getQuantity();
		ProductData getProduct();
		interface ProductData{
			long getId();
			String getName();
			BigDecimal getListingPrice();
		}
	}
	
	interface SupplierData{
		long getId();
		String getName();
	}
}
