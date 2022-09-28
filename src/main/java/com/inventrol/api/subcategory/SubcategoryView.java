package com.inventrol.api.subcategory;

import java.time.LocalDate;

public interface SubcategoryView {
	long getId();
	String getName();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	CategoryInfo getCategory();
	interface CategoryInfo{
		long getId();
		String getName();
	}
}
