package com.inventrol.api.attributevalue;

import java.time.LocalDate;

public interface AttributeValueView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	AttributeData getAttribute();
	interface AttributeData{
		long getId();
		String getName();
		 String getTagColor() ;
	}
}
