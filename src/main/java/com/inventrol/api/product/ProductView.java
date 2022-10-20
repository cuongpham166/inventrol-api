package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.inventrol.api.product.ProductDetailView.AttributeValueData.AttributeData;

public interface ProductView {
	long getId();
	String getName();
	BigDecimal getVat();
	 BigDecimal getRetailPrice();
	 BigDecimal getListingPrice();
	String getNotice();
	String getStockStatus();
	boolean isDeleted();
	 String getSku();
	 int getQuantity();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	SubcategoryData getSubcategory();
	Set<AttributeValueData>getAttributeValue();
	BrandData getBrand();
	
	interface SubcategoryData{
		long getId();
		String getName();	
		String getTagColor();
		CategoryData getCategory();
		interface CategoryData{
			long getId();
			String getName();
			String getTagColor();
		}
	}
	
	interface AttributeValueData{
		long getId();
		String getName();
		AttributeData getAttribute();
		interface AttributeData{
			long getId();
			String getName();
			String getTagColor();
		}
	}
	
	interface BrandData{
		long getId();
		String getName();
	}
}
