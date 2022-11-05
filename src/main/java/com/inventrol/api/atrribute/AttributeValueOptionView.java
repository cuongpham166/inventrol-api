package com.inventrol.api.atrribute;

import java.util.Set;

public interface AttributeValueOptionView {
	long getId();
	String getName();
	boolean isDeleted();
	Set<AttributeValueData> getAttributevalue();
	interface AttributeValueData{
		long getId();
		String getName();	
		boolean isDeleted();
	}
}
