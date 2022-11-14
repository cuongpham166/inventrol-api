package com.inventrol.api.supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

public interface SupplierView {
	long getId();
	String getName();
	String getContactPerson();
	 String getEmail();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	ContactData getContact();
	 boolean isDeleted();
	 
	interface ContactData{
		long getId();
		String getAdditionalAddressLine();
		String getPhoneNumber();
		String getMobileNumber();
		String getCountry();
		 
	    @Value("#{target.streetName + ' ' + target.streetNumber}")
	    String getMainAddressLine();
	   
	    @Value("#{target.postcode + ' ' + target.city}")
	    String getCityInfo();
	}
}
