package com.inventrol.api.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SubcategoryController {

	@Autowired
	private SubcategoryService subcategoryService;
	
	@GetMapping("/subcategory")
	public ResponseEntity<List<SubcategoryView>> getAllSubcategories() {
		try {
			List<SubcategoryView> subcategories = subcategoryService.getAllSubcategories();
			if (subcategories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(subcategories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/subcategory")
	public ResponseEntity<Subcategory> createSubcategory (@RequestBody Subcategory newSubcategory){
		try {
			Subcategory _subcategory = subcategoryService.createSubcategory(newSubcategory);
			return new ResponseEntity<>(_subcategory, HttpStatus.CREATED);
			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
