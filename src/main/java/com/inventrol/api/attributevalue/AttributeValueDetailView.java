package com.inventrol.api.attributevalue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.inventrol.api.brand.BrandDetailView.ProductData.AttributeValueData;
import com.inventrol.api.brand.BrandDetailView.ProductData.BrandData;
import com.inventrol.api.brand.BrandDetailView.ProductData.SubcategoryData;
import com.inventrol.api.brand.BrandDetailView.ProductData.AttributeValueData.AttributeData;
import com.inventrol.api.brand.BrandDetailView.ProductData.SubcategoryData.CategoryData;

public interface AttributeValueDetailView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	
	AttributeData getAttribute();
	Set<ProductData>getProduct();
	
	interface AttributeData{
		long getId();
		String getName();
		 String getTagColor();
	}
	
	interface ProductData {
		long getId();
		String getName();
		BigDecimal getRetailPrice();
		BigDecimal getListingPrice();
		String getNotice();
		String getStockStatus();
		boolean isDeleted();
		String getSku();
		int getQuantity();

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
}
