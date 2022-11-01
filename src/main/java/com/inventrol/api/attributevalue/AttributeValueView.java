package com.inventrol.api.attributevalue;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AttributeValueView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	AttributeData getAttribute();
	interface AttributeData{
		long getId();
		String getName();
		 String getTagColor() ;
	}
}
