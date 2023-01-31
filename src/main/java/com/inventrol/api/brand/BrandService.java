package com.inventrol.api.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.meilisearch.sdk.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements BrandInterface{

	@Autowired
	private BrandRepository brandRepo;
	
	public Optional<Brand> getBrandById(long id){
		Optional<Brand>foundBrand = brandRepo.findById(id);
		return foundBrand;
	}
	
	public List<BrandView> getAllBrands (){
		List<BrandView>brands = new ArrayList<BrandView>();
		brandRepo.findAllProjectedByOrderByIdAsc(BrandView.class).forEach(brands::add);
		List<BrandView> result = brands.stream().filter(res -> res.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public List<BrandView>searchBrand (String name){
		List<BrandView>foundBrands = new ArrayList<BrandView>();
		brandRepo.findProjectedByNameContainsIgnoreCase(name, BrandView.class).forEach(foundBrands::add);
		List<BrandView> result = foundBrands.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public BrandDetailView getBrandDetailById(long id) {
		BrandDetailView brandDetail = brandRepo.findProjectedById(id, BrandDetailView.class);
		return brandDetail;
	}
	
	public void createBrand (Brand newBrand) {
		brandRepo.save(newBrand);
	}
	
	public void updateBrand (long brandId, Brand updatedBrand) {
		Brand foundBrand = getBrandById(brandId).get();
		foundBrand.setName(updatedBrand.getName());
		foundBrand.setNotice(updatedBrand.getNotice());
		brandRepo.save(foundBrand);
	}
}
