package com.inventrol.api.brand;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.auth.MessageResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BrandController {
	@Autowired
	private  BrandService brandService;
	
	@Autowired
	private BrandRepository brandRepo;

	@GetMapping("/brand")
	public ResponseEntity<List<BrandView>> getAllBrands(@RequestParam Optional<String> name) {
		try {
			List<BrandView> brands = new ArrayList<BrandView>();
			if(name.isPresent()) {
				brands = brandService.searchBrand(name.get());
			}else {
				brands = brandService.getAllBrands();
			}
			if (brands.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(brands, HttpStatus.OK);
		} catch (Exception e) {
			System.out.printf(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/brand/{id}")
	public ResponseEntity<BrandDetailView>getBrandById(@PathVariable("id") long id){
		Optional<Brand> brandData = brandService.getBrandById(id);
		if(brandData.isPresent()) {
			Brand _brand = brandData.get();
			if(_brand.isDeleted() == false) {
				BrandDetailView _brandDetail = brandService.getBrandDetailById(id);
				return new ResponseEntity<>(_brandDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/brand/{id}/products")
	public ResponseEntity<Set<BrandDetailView.ProductData>>getProductsByBrandId(@PathVariable("id") long id){
		Optional<Brand> brandData = brandService.getBrandById(id);
		if(brandData.isPresent()) {
			Brand _brand = brandData.get();
			if(_brand.isDeleted() == false) {
				BrandDetailView _brandDetail = brandService.getBrandDetailById(id);
				Set<BrandDetailView.ProductData> _products = new HashSet<BrandDetailView.ProductData>();
				_products = _brandDetail.getProduct();
				return new ResponseEntity<>(_products, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/brand")
	public ResponseEntity<?> createBrand(@RequestBody Brand newBrand){
		try{
			if(brandRepo.existsBrandByName(newBrand.getName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
			}
			brandService.createBrand(newBrand);
			return ResponseEntity.ok().body(new MessageResponse("Success:  A new brand has been created"));
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  Internal Server Error"));
		}
	}
	
	@PutMapping("/brand/{brandId}")
	public ResponseEntity<?>updateBrand(@PathVariable("brandId")long brandId,@RequestBody Brand updatedBrand ){
		Optional<Brand>brandData = brandService.getBrandById(brandId);
		if(brandData.isPresent()) {
			Brand _brand = brandData.get();
			if(_brand.isDeleted() == false) {
				brandService.updateBrand(brandId, updatedBrand);
				return ResponseEntity.ok().body(new MessageResponse("Success: Category has been updated"));
			}
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
