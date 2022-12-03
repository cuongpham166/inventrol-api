package com.inventrol.api.product;

import java.util.List;
import java.util.Optional;

import com.inventrol.api.product.productstock.ProductStockView;

public interface ProductInterface {
	public Optional<Product> getProductById(long id);
	public List<ProductView> getAllProducts ();
	public List<ProductStockView>getAllProductStock();
	public List<ProductView>searchProduct (String name);
	public ProductDetailView getProductDetailById(long id);
	public String createSKU (Product newProduct);
	public void saveNewProduct (Product newProduct);
}
