package com.inventrol.api.category;

import java.time.LocalDateTime;
import java.util.Set;

public interface CategoryView {
	long getId();
	String getName();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	String getTagColor();
	boolean isDeleted();
	Set<SubcategoryData> getSubcategory();
		interface SubcategoryData {
			long getId();
			String getName();	
			String getTagColor();
		}
	 
}
