package com.inventrol.api.customer.customeraddress;

import java.time.LocalDateTime;
import java.util.Set;

public interface CustomerAddressDetailView {
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
