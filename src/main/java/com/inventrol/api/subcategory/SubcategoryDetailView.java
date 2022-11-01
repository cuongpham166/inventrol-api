package com.inventrol.api.subcategory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SubcategoryDetailView {
	long getId();
	String getName();
	String getTagColor();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	categoryData getCategory();
	 boolean isDeleted();
	interface categoryData{
		long getId();
		String getName();		
		String getTagColor();
	}
}
