package com.inventrol.api.atrribute;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface AttributeView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	String getTagColor();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	Set<AttributeValueData> getAttributevalue();
	interface AttributeValueData{
		long getId();
		String getName();		
	}
}
