package com.inventrol.api.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface CustomerDetailView {
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
	Set<CustomerAddress>getCustomeradress();
	interface CustomerAddress{
		long getId();
		String getStreetName();
		String getStreetNumber();
		String getAdditionalAddressLine();
		String getPostcode();
		String getCity();
		String getCountry();
		String getNotice();
		LocalDateTime getCreatedOn();
		LocalDateTime getUpdatedOn();
		boolean isPrimary();
		boolean isDeleted();
	}
}
