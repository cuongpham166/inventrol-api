package com.inventrol.api.supplier;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public interface SupplierDetailView {
	long getId();
	String getName();
	String getContactPerson();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	ContactInfo getContact();
	
	interface ContactInfo{
		long getId();
		String getWebsite();
		String getAdditionalAddressLine();
		String getEmail();
		String getPhoneNumber();
		String getMobileNumber();
		String getCountry();
		 
	    @Value("#{target.streetName + ' ' + target.streetNumber}")
	    String getMainAddressLine();
	   
	    @Value("#{target.postcode + ' ' + target.city}")
	    String getCityInfo();
	    
	    
	}
}
