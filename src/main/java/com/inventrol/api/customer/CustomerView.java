package com.inventrol.api.customer;

import java.time.LocalDate;
import java.util.Set;

public interface CustomerView {
	long getId();
	String getName();
	String getEmail();
	String getMobileNumber();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	boolean isDeleted();
}
