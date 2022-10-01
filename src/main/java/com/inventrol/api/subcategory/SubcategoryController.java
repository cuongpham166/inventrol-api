package com.inventrol.api.subcategory;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.category.Category;
import com.inventrol.api.category.CategoryDetailView;

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
	
	@GetMapping("/subcategory/{id}")
	public ResponseEntity<SubcategoryDetailView>getCategoryById(@PathVariable("id") long id){
		Optional<Subcategory> subcategoryData = subcategoryService.getSubcategoryById(id);
		if(subcategoryData.isPresent()) {
			SubcategoryDetailView _subcategoryDetail = subcategoryService.getSubcategoryDetailById(id);
			return new ResponseEntity<>(_subcategoryDetail, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/subcategory")
	public ResponseEntity<Subcategory> createSubcategory (@RequestBody Subcategory newSubcategory){
		try {
			subcategoryService.createSubcategory(newSubcategory);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/subcategory/{id}")
	public ResponseEntity<Subcategory>updateSubcategory(@PathVariable("id") long id,@RequestBody Subcategory updatedSubcategory ){
		Optional<Subcategory>subcategoryData = subcategoryService.getSubcategoryById(id);
		if(subcategoryData.isPresent()) {
			subcategoryService.updateSubcategory(id, updatedSubcategory);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
