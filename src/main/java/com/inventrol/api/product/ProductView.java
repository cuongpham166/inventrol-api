package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.inventrol.api.product.ProductDetailView.AttributeValueData.AttributeData;

public interface ProductView {
	long getId();
	String getName();
	BigDecimal getVat();
	BigDecimal getRetailPrice();
	BigDecimal getListingPrice();
	String getNotice();
	boolean isDeleted();
	String getSku();
	String getBarcode();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	SubcategoryData getSubcategory();
	Set<AttributeValueData>getAttributeValue();
	BrandData getBrand();
	DiscountData getDiscount();
	ProductStockData getProductstock();
	
	interface ProductStockData {
		int getQuantity();
		int getSoldNumber();
		int getOrderedNumber();
		String getStockStatus();
		String getNotice();
		boolean isDeleted();
		LocalDateTime getUpdatedOn();
		String getUpdatedBy();
	}
	interface DiscountData{
		long getId();
		BigDecimal getDiscountPercent();
		boolean isDeleted();
	}
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
