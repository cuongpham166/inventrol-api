package com.inventrol.api.brand;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

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
	
	public BrandDetailView getBrandDetailById(long id) {
		BrandDetailView brandDetail = brandRepo.findProjectedById(id, BrandDetailView.class);
		return brandDetail;
	}
	
	public void createBrand (Brand newBrand) {
		newBrand.setCreatedDate(LocalDate.now());
		brandRepo.save(newBrand);
	}
}
