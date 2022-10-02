package com.inventrol.api.atrribute;

import java.time.LocalDate;
import java.util.Set;

public interface AttributeView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	Set<AttributeValueData> getAttributevalue();
	interface AttributeValueData{
		long getId();
		String getName();		
	}
}
