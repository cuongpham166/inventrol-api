package com.inventrol.api.brand;

import java.util.List;
import java.util.Optional;

public interface BrandInterface {
	public Optional<Brand> getBrandById(long id);
	public List<BrandView> getAllBrands ();
	public List<BrandView>searchBrand (String name);
	public BrandDetailView getBrandDetailById(long id);
	public void createBrand (Brand newBrand);
	public void updateBrand (long brandId, Brand updatedBrand);
}
