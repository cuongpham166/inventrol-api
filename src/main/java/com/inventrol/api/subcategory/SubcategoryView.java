package com.inventrol.api.subcategory;

import java.time.LocalDate;

public interface SubcategoryView {
	long getId();
	String getName();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	CategoryData getCategory();
	String getTagColor();
	 boolean isDeleted();
	interface CategoryData{
		long getId();
		String getName();
		String getTagColor();
	}
}
