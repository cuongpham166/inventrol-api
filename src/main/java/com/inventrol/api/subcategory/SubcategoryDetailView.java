package com.inventrol.api.subcategory;

import java.time.LocalDate;

public interface SubcategoryDetailView {
	long getId();
	String getName();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	categoryData getCategory();
	 boolean isDeleted();
	interface categoryData{
		long getId();
		String getName();		
	}
}
