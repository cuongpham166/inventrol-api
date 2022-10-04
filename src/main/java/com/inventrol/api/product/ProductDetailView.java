package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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
	}
}
