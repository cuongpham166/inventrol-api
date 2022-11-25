package com.inventrol.api.order.orderpayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderPaymentView {
	 long getId();
	 OrderData getOrder();
	 String getStatus();
	 String getPaymentType();
	 String getNotice();
	 BigDecimal getTotal();
	 BigDecimal getPaid();
	 LocalDateTime getCreatedOn();
	 LocalDateTime getUpdatedOn();

	 boolean isDeleted();
	 interface OrderData {
		 long getId();
		 boolean isDeleted();
	 }
}
