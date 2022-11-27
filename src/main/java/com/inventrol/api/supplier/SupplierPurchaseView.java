package com.inventrol.api.supplier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface SupplierPurchaseView {
	long getId();
	String getName();
	Set<PurchaseData> getPurchase();
	
	interface PurchaseData {
		long getId();
		PurchaseShippingData getPurchaseshipping();
		Set<PurchaseItemData> getPurchaseItem();
		int getNumberOfItems();
		BigDecimal getTotal();
		String getNotice();
		String getPaymentType();
		LocalDateTime getCreatedOn();
		String getCreatedBy();
		boolean isDeleted();

		interface PurchaseShippingData {
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
		
		
	}
}
