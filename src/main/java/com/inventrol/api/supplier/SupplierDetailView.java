package com.inventrol.api.supplier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.product.ProductView.AttributeValueData;
import com.inventrol.api.product.ProductView.BrandData;
import com.inventrol.api.product.ProductView.SubcategoryData;
import com.inventrol.api.product.ProductView.AttributeValueData.AttributeData;
import com.inventrol.api.product.ProductView.SubcategoryData.CategoryData;

public interface SupplierDetailView {
	long getId();
	String getName();
	String getContactPerson();
	String getNotice();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	ContactData getContact();
	boolean isDeleted();
	
	Set<ProductData>getProduct();
	
	interface ContactData{
		long getId();
		String getWebsite();
		String getAdditionalAddressLine();
		String getEmail();
		String getPhoneNumber();
		String getMobileNumber();
		String getStreetName();
		String getStreetNumber();
		String getPostcode();
		String getCity();
		String getCountry();
		 
	    @Value("#{target.streetName + ' ' + target.streetNumber}")
	    String getMainAddressLine();
	   
	    @Value("#{target.postcode + ' ' + target.city}")
	    String getCityInfo();
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
