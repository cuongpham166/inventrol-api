package com.inventrol.api.order.orderpayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inventrol.api.order.ordershipping.OrderShippingView.CustomerData;

public interface OrderPaymentView {
	 long getId();
	 boolean isDeleted();
	 OrderPaymentData getPayment();
	 CustomerData getCustomer();
	 
	 interface CustomerData {
		long getId();
		String getName();
		boolean isDeleted();
	}
		
	 interface OrderPaymentData {
		 long getId();
		 String getStatus();
		 String getPaymentType();
		 String getNotice();
		 BigDecimal getTotal();
		 BigDecimal getPaid();
		 LocalDateTime getCreatedOn();
		 LocalDateTime getUpdatedOn();
		 boolean isDeleted();
	 }

}
