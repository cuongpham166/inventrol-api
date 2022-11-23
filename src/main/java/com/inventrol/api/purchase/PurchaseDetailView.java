package com.inventrol.api.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.attributevalue.AttributeValue;

public interface PurchaseDetailView {
	long getId();
	
	SupplierData getSupplier();
	PurchaseShippingData getPurchaseshipping();
	Set<PurchaseItemData> getPurchaseItem();
	Set<PurchaseHistoryData> getPurchasehistory();
	
	int getNumberOfItems();
	BigDecimal getTotal();
	String getNotice();
	String getPaymentType();
	LocalDateTime getCreatedOn();
	String getCreatedBy();
	boolean isDeleted();
	
	interface PurchaseShippingData{
		long getId();
		String getService();
		String getTrackingNumber();
		String getStatus();
		String getNotice();
		LocalDateTime getUpdatedOn();
	}
	
	interface PurchaseHistoryData {
		long getId();
		String getStatus();
		LocalDateTime getCreatedOn();
	}
	
	interface PurchaseItemData {
		long getId();
		int getQuantity();
		ProductData getProduct();
		interface ProductData{
			long getId();
			String getName();
			String getBarcode();
			BigDecimal getListingPrice();
			BrandData getBrand();
			SubcategoryData getSubcategory();
			
			Set<AttributeValue> getAttributeValue();
			interface BrandData {
				long getId();
				String getName();
			}
			
			interface SubcategoryData {
				long getId();
				String getName();
				 String getTagColor();
			}
			
			interface AttributeValue {
				long getId();
				String getName();
			}
		}
	}
	
	interface SupplierData{
		long getId();
		String getName();
		String getContactPerson();
		String getWebsite();
		String getEmail();
		ContactData getContact();
		interface ContactData {
			String getPhoneNumber();
			String getMobileNumber();
			String getAdditionalAddressLine();
		    @Value("#{target.streetName + '. ' + target.streetNumber + ', ' +target.postcode+ ' '+target.city+ ', '+target.country}")
		    String getAddress();
		}
	}
}
