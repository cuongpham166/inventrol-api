package com.inventrol.api.customer;

import java.time.LocalDate;
import java.util.Set;

public interface CustomerDetailView {
	long getId();
	String getName();
	String getEmail();
	String getMobileNumber();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
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
		LocalDate getCreatedDate();
		LocalDate getUpdatedDate();
		boolean isPrimary();
		boolean isDeleted();
	}
}
