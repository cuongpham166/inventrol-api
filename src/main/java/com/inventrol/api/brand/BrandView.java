package com.inventrol.api.brand;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BrandView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
}
