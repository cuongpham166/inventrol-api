package com.inventrol.api.category;

import java.time.LocalDateTime;
import java.util.Set;

public interface CategoryDetailView {
	long getId();
	String getName();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	 boolean isDeleted();
	 String getTagColor();
	Set<SubcategoryData> getSubcategory();
	interface SubcategoryData {
		long getId();
		String getName();
		String getTagColor();
	}
}
