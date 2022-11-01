package com.inventrol.api.brand;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.inventrol.api.supplier.SupplierDetailView.ProductData.AttributeValueData;
import com.inventrol.api.supplier.SupplierDetailView.ProductData.BrandData;
import com.inventrol.api.supplier.SupplierDetailView.ProductData.SubcategoryData;
import com.inventrol.api.supplier.SupplierDetailView.ProductData.AttributeValueData.AttributeData;
import com.inventrol.api.supplier.SupplierDetailView.ProductData.SubcategoryData.CategoryData;

public interface BrandDetailView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	Set<ProductData> getProduct();
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
