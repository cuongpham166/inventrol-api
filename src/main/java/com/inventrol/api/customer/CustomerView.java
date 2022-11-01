package com.inventrol.api.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface CustomerView {
	long getId();
	String getName();
	String getEmail();
	String getMobileNumber();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	boolean isDeleted();
}
