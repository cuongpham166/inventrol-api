package com.inventrol.api.category;

import java.time.LocalDate;
import java.util.Set;

public interface CategoryView {
	long getId();
	String getName();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	String getTagColor();
	 boolean isDeleted();
		Set<SubcategoryData> getSubcategory();
		interface SubcategoryData {
			long getId();
			String getName();	
			String getTagColor();
		}
	 
}
