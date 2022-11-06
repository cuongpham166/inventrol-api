package com.inventrol.api.category;

import java.time.LocalDateTime;
import java.util.Set;

public interface SubcategoryOptionView {
	long getId();
	String getName();
	boolean isDeleted();
	Set<SubcategoryData> getSubcategory();
	interface SubcategoryData {
		long getId();
		String getName();
		boolean isDeleted();
	}
}
