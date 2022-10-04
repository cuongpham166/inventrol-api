package com.inventrol.api.brand;

import java.time.LocalDate;
import java.util.Set;

public interface BrandDetailView {
	long getId();
	String getName();
	String getNotice();
	boolean isDeleted();
	LocalDate getCreatedDate();
	LocalDate getUpdatedDate();
	Set<ProductData> getProduct();
	interface ProductData{
		long getId();
		String getName();
	}
}
