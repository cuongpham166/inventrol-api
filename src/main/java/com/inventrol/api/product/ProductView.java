package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface ProductView {
	long getId();
	String getName();
	 BigDecimal getRetailPrice();
	 BigDecimal getListingPrice();
	String getNotice();
	String getStockStatus();
	boolean isDeleted();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	SubcategoryData getSubcategory();
	Set<AttributeValueData>getAttributeValue();
	BrandData getBrand();
	
	interface SubcategoryData{
		long getId();
		String getName();	
		CategoryData getCategory();
		interface CategoryData{
			long getId();
			String getName();
		}
	}
	
	interface AttributeValueData{
		long getId();
		String getName();
	}
	
	interface BrandData{
		long getId();
		String getName();
	}
}
