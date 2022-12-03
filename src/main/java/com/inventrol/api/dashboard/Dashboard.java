package com.inventrol.api.dashboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.inventrol.api.product.ProductView;
import com.inventrol.api.product.productstock.ProductStockView;
import com.inventrol.api.purchase.PurchaseView;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShippingStatusView;

public class Dashboard {
	

	public Dashboard(long totalProducts, long totalBrands, long totalCategories, long totalSubcategories,
			long totalAttributes, long totalCustomers, long totalSuppliers, List<ProductStockView> stock,
			List<PurchaseShippingStatusView> purchaseShippingStatus) {
		super();
		this.totalProducts = totalProducts;
		this.totalBrands = totalBrands;
		this.totalCategories = totalCategories;
		this.totalSubcategories = totalSubcategories;
		this.totalAttributes = totalAttributes;
		this.totalCustomers = totalCustomers;
		this.totalSuppliers = totalSuppliers;
		this.stock = stock;
		this.purchaseShippingStatus = purchaseShippingStatus;
	}

	public Dashboard() {
		super();
	}
	

	//Product List ProductView
	//Purchase List PurchaseView
	//Order Payment List OrderView
	//Order List OrderView
		
	//Total Products
	//Total Brands
	//Total Categories
	// Total Subcategories
	//Total Attributes
	// Total Suppliers
	//Total Customers

	private long totalProducts;
	private long totalBrands;
	private long totalCategories;
	private long totalSubcategories;
	private long totalAttributes;
	private long totalCustomers;
	private long totalSuppliers;
	
	private List<ProductStockView>stock = new ArrayList<ProductStockView>();
	
	private List<PurchaseShippingStatusView>purchaseShippingStatus = new ArrayList<PurchaseShippingStatusView>();

	public long getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(long totalProducts) {
		this.totalProducts = totalProducts;
	}

	public long getTotalBrands() {
		return totalBrands;
	}

	public void setTotalBrands(long totalBrands) {
		this.totalBrands = totalBrands;
	}

	public long getTotalCategories() {
		return totalCategories;
	}

	public void setTotalCategories(long totalCategories) {
		this.totalCategories = totalCategories;
	}

	public long getTotalSubcategories() {
		return totalSubcategories;
	}

	public void setTotalSubcategories(long totalSubcategories) {
		this.totalSubcategories = totalSubcategories;
	}

	public long getTotalAttributes() {
		return totalAttributes;
	}

	public void setTotalAttributes(long totalAttributes) {
		this.totalAttributes = totalAttributes;
	}

	public long getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}


	public long getTotalSuppliers() {
		return totalSuppliers;
	}

	public void setTotalSuppliers(long totalSuppliers) {
		this.totalSuppliers = totalSuppliers;
	}




	public List<PurchaseShippingStatusView> getPurchaseShippingStatus() {
		return purchaseShippingStatus;
	}

	public void setPurchaseShippingStatus(List<PurchaseShippingStatusView> purchaseShippingStatus) {
		this.purchaseShippingStatus = purchaseShippingStatus;
	}

	public List<ProductStockView> getStock() {
		return stock;
	}

	public void setStock(List<ProductStockView> stock) {
		this.stock = stock;
	}
}
