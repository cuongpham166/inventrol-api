package com.inventrol.api.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BrandController {
	@Autowired
	private  BrandService brandService;

	@GetMapping("/brand")
	public ResponseEntity<List<BrandView>> getAllBrands() {
		try {
			List<BrandView> brands = brandService.getAllBrands();
			if (brands.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(brands, HttpStatus.OK);
		} catch (Exception e) {
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
	
	@PostMapping("/brand")
	public ResponseEntity<Brand> createBrand(@RequestBody Brand newBrand){
		try{
			brandService.createBrand(newBrand);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
