package com.inventrol.api.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface OrderView {
	 long getId();
	 Set<OrderItemData> getOrderitem();
	 CustomerData getCustomer();
	 OrderShippingData getShipping();
	 String getNotice();
	 boolean isDeleted();
	 LocalDateTime getCreatedOn();
	 BigDecimal getTotal();
	 
	 interface OrderItemData {
			long getId();
			int getQuantity();
			ProductData getProduct();
			interface ProductData{
				long getId();
				String getName();
				int getVat();
				BigDecimal getRetailPrice();
			}
	 }
	 
	 interface CustomerData {
			long getId();
			String getName();
	 }
	 
	 interface OrderShippingData {
		 String getStatus();
	 }
}
