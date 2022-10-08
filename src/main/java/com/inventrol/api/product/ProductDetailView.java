package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

public interface ProductDetailView {
	long getId();
	String getName();
	int getQuantity();
	int getSoldNumber();
	int getOrderedNumber();
	String getBarcode();
	String getSku();
	BigDecimal getVat();
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
	Set<ListingPriceRecordData>getListingPriceRecord();
	Set<RetailPriceRecordData>getRetailPriceRecord();
	Set<SupplierData>getSupplier();
	
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
	
	interface ListingPriceRecordData{
		BigDecimal getPrice();
		LocalDate getCreatedDate();
	}
	
	interface RetailPriceRecordData{
		BigDecimal getPrice();
		LocalDate getCreatedDate();		
	}
	
	interface SupplierData{
		long getId();
		String getName();	
		String getContactPerson();
		String getNotice();
		boolean isDeleted();
		ContactData getContact();
		interface ContactData {
			String getWebsite();
			String getAdditionalAddressLine();
			String getEmail();
			String getPhoneNumber();
			String getMobileNumber();
			String getCountry();
			 
		    @Value("#{target.streetName + ' ' + target.streetNumber}")
		    String getMainAddressLine();
		   
		    @Value("#{target.postcode + ' ' + target.city}")
		    String getCityInfo();			
		}
	}
}
