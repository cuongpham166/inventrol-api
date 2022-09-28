package com.inventrol.api.category;

import java.time.LocalDate;
import java.util.Set;

public interface CategoryView {
	long getId();
	String getName();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	Set<subcategoryData> getSubcategory();
	interface subcategoryData {
		long getId();
		String getName();	
	}
}
