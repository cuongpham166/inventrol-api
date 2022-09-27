package com.inventrol.api.category;

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

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private  CategoryService categoryService;

	@GetMapping("/category")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Category>> getAllCategories() {
		try {
			List<Category> categories = categoryService.getAllCategories();
			if (categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/category/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Category>getCategoryById(@PathVariable("id") long id){
		Optional<Category> categoryData = categoryService.getCategoryById(id);
		if(categoryData.isPresent()) {
			return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/category")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Category> createCategory(@RequestBody Category newCategory){
		try{
			Category _category = categoryService.createCategory(newCategory);
			return new ResponseEntity<>(_category, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/category/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Category>updateCategory(@PathVariable("id") long id,@RequestBody Category updatedCategory ){
		Optional<Category>categoryData = categoryService.getCategoryById(id);
		if(categoryData.isPresent()) {
			Category _category = categoryService.updateCategory(id, updatedCategory);
			return new ResponseEntity<>(_category, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
