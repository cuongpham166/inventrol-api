package com.inventrol.api.subcategory;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.auth.MessageResponse;
import com.inventrol.api.category.Category;
import com.inventrol.api.category.CategoryDetailView;
import com.inventrol.api.category.CategoryRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SubcategoryController {

	@Autowired
	private SubcategoryService subcategoryService;
	
	@Autowired
	private SubcategoryRepository subcategoryRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping("/subcategory")
	public ResponseEntity<List<SubcategoryView>> getAllSubcategories(@RequestParam Optional<String> name) {
		try {
			List<SubcategoryView> subcategories = new ArrayList<SubcategoryView>();
			if(name.isPresent()) {
				subcategories = subcategoryService.searchSubcategory(name.get());
			}else {
				subcategories = subcategoryService.getAllSubcategories();
			}
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
			Subcategory _subcategory = subcategoryData.get();
			if(_subcategory.isDeleted() == false) {
				SubcategoryDetailView _subcategoryDetail = subcategoryService.getSubcategoryDetailById(id);
				return new ResponseEntity<>(_subcategoryDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/subcategory")
	public ResponseEntity<?> createSubcategory (@RequestBody Subcategory newSubcategory){
		try {
			if(subcategoryRepo.existsSubcategoryByName(newSubcategory.getName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
			}else {
				if(categoryRepo.existsCategoryByName(newSubcategory.getCategory().getName())) {
					subcategoryService.createSubcategory(newSubcategory);
					return ResponseEntity.ok().body(new MessageResponse("Success:  A new subcategory has been created"));
				}
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This category does not exist"));
			}
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  Internal Server Error"));
		}
	}
	
	@PutMapping("/subcategory/{id}")
	public ResponseEntity<?>updateSubcategory(@PathVariable("id") long id,@RequestBody Subcategory updatedSubcategory ){
		Optional<Subcategory>subcategoryData = subcategoryService.getSubcategoryById(id);
		if(subcategoryData.isPresent()) {
			Subcategory _subcategory = subcategoryData.get();
			if(_subcategory.isDeleted() == false) {
				if(subcategoryRepo.existsSubcategoryByName(updatedSubcategory.getName())) {
					return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
				}else {
					if(categoryRepo.existsCategoryByName(updatedSubcategory.getCategory().getName())) {
						subcategoryService.updateSubcategory(id, updatedSubcategory);
						return ResponseEntity.ok().body(new MessageResponse("Success: Subcategory has been updated"));
					}
					return ResponseEntity.badRequest().body(new MessageResponse("Error: This category does not exist"));
				}
			}else {
				return ResponseEntity.notFound().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
