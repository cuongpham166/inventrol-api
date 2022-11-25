package com.inventrol.api.category;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.inventrol.api.product.Product;
import com.inventrol.api.product.ProductView.AttributeValueData;
import com.inventrol.api.product.ProductView.BrandData;
import com.inventrol.api.product.ProductView.DiscountData;
import com.inventrol.api.product.ProductView.ProductStockData;
import com.inventrol.api.product.ProductView.SubcategoryData;
import com.inventrol.api.product.ProductView.AttributeValueData.AttributeData;
import com.inventrol.api.product.ProductView.SubcategoryData.CategoryData;

public interface CategoryDetailView {
	long getId();
	String getName();
	String getNotice();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	boolean isDeleted();
	String getTagColor();
	Set<SubcategoryData> getSubcategory();
	interface SubcategoryData {
		long getId();
		String getName();
		String getTagColor();
		Set<ProductData> getProduct();
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
}
