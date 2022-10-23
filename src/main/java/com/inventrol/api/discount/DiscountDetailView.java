package com.inventrol.api.discount;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DiscountDetailView {
	long getId();
	BigDecimal getDiscountPercent();
	String getNotice();
	boolean isDeleted();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
}
