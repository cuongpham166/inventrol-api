package com.inventrol.api.discount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DiscountView {
	long getId();
	BigDecimal getDiscountPercent();
	String getNotice();
	boolean isDeleted();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
}
