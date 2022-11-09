package com.inventrol.api.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface PurchaseDetailView {
	long getId();
	Set<PurchaseItemData> getPurchaseItem();
	SupplierData getSupplier();
	int getNumberOfItems();
	String getStatus();
	BigDecimal getTotal();
	String getNotice();
	String getPaymentType();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	boolean isDeleted();
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
