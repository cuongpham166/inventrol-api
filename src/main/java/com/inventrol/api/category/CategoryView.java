package com.inventrol.api.category;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.subcategory.SubcategoryView.CategoryData;

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
		String getNotice();
		LocalDateTime getCreatedOn();
		String getCreatedBy();
		LocalDateTime getUpdatedOn();
		String getUpdatedBy();
		String getTagColor();
	}
	 
}
