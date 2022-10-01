package com.inventrol.api.supplier;

import java.time.LocalDate;

public interface SupplierView {
	long getId();
	String getName();
	String getContactPerson();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	ContactInfo getContact();
	
	interface ContactInfo{
		long getId();
	}
}
