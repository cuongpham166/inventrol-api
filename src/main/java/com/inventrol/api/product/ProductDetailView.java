package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.product.ProductView.ProductStockData;

public interface ProductDetailView {
	long getId();
	String getName();
	String getBarcode();
	String getSku();
	BigDecimal getVat();
	BigDecimal getRetailPrice();
	BigDecimal getListingPrice(); 
	String getNotice();
	boolean isDeleted();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	LocalDateTime getUpdatedOn();
	String getUpdatedBy();
	
	SubcategoryData getSubcategory();
	
	Set<AttributeValueData>getAttributeValue();
	BrandData getBrand();
	Set<ListingPriceRecordData>getListingPriceRecord();
	Set<RetailPriceRecordData>getRetailPriceRecord();
	Set<SupplierData>getSupplier();
	ProductStockData getProductstock();
	DiscountData getDiscount();
	OrderItemData getOrderItem();
	//PurchaseItemData getPurchaseItem();
	
	interface OrderItemData {
		int getQuantity();
		OrderData getOrder();
		interface OrderData{
			LocalDateTime getCreatedOn();
		}
	}
	
	interface PurchaseItemData{
		int getQuantity();
		PurchaseData getPurchase();
		interface PurchaseData{
			LocalDateTime getCreatedOn();
		}
	}
	
	
	interface DiscountData{
		long getId();
		BigDecimal getDiscountPercent();
		boolean isDeleted();
	}
	
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
		 LocalDateTime getCreatedOn();
	}
	
	interface RetailPriceRecordData{
		BigDecimal getPrice();
		LocalDateTime getCreatedOn();
	}
	
	interface SupplierData{
		long getId();
		String getName();	
		String getContactPerson();
		String getNotice();
		String getEmail();
		String getWebsite();
		boolean isDeleted();
		ContactData getContact();
		interface ContactData {
			
			String getAdditionalAddressLine();
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
