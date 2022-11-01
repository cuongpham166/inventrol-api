package com.inventrol.api.subcategory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SubcategoryView {
	long getId();
	String getName();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	CategoryData getCategory();
	String getTagColor();
	boolean isDeleted();
	interface CategoryData{
		long getId();
		String getName();
		String getTagColor();
	}
}
