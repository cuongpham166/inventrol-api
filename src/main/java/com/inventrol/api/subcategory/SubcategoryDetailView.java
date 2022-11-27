package com.inventrol.api.subcategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.inventrol.api.category.CategoryDetailView.SubcategoryData.ProductData;
import com.inventrol.api.category.CategoryDetailView.SubcategoryData.ProductData.AttributeValueData;
import com.inventrol.api.category.CategoryDetailView.SubcategoryData.ProductData.BrandData;
import com.inventrol.api.category.CategoryDetailView.SubcategoryData.ProductData.DiscountData;
import com.inventrol.api.category.CategoryDetailView.SubcategoryData.ProductData.ProductStockData;
import com.inventrol.api.category.CategoryDetailView.SubcategoryData.ProductData.AttributeValueData.AttributeData;

public interface SubcategoryDetailView {
	long getId();
	String getName();
	String getTagColor();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	CategoryData getCategory();
	Set<ProductData> getProduct();
	boolean isDeleted();
	interface CategoryData{
		long getId();
		String getName();		
		String getTagColor();
	}
	
	interface ProductData{
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
		Set<AttributeValueData>getAttributeValue();
		BrandData getBrand();
		DiscountData getDiscount();
		ProductStockData getProductstock();
		
		interface ProductStockData {
			int getQuantity();
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
	
}
