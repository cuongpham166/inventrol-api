package com.inventrol.api.supplier;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

public interface SupplierDetailView {
	long getId();
	String getName();
	String getContactPerson();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	ContactData getContact();
	boolean isDeleted();
	
	Set<ProductData>getProduct();
	interface ContactData{
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
	
	interface ProductData {
		
	}
}
