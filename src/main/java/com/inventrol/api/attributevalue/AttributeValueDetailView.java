package com.inventrol.api.attributevalue;

import java.time.LocalDate;
import java.util.Set;

public interface AttributeValueDetailView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	
	AttributeData getAttribute();
	Set<ProductData>getProduct();
	
	interface AttributeData{
		long getId();
		String getName();
		 String getTagColor();
	}
	
	interface ProductData{
		long getId();
		String getName();
	}
}
