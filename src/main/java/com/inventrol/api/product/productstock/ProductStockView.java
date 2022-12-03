package com.inventrol.api.product.productstock;

public interface ProductStockView {
	long getId();
	ProductStockData getProductstock();
	interface ProductStockData {
		String getStockStatus();
	}
	
}
