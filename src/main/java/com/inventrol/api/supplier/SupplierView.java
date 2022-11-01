package com.inventrol.api.supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SupplierView {
	long getId();
	String getName();
	String getContactPerson();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	ContactInfo getContact();
	 boolean isDeleted();
	 
	interface ContactInfo{
		long getId();
	}
}
