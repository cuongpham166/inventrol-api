package com.inventrol.api.category;

import java.time.LocalDate;
import java.util.Set;

public interface CategoryDetailView {
	long getId();
	String getName();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	 boolean isDeleted();
	Set<subcategoryData> getSubcategory();
	interface subcategoryData {
		long getId();
		String getName();	
	}
}
