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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private  CategoryService categoryService;

	@GetMapping("/category")
	public ResponseEntity<List<CategoryView>> getAllCategories() {
		try {
			List<CategoryView> categories = categoryService.getAllCategories();
			if (categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<CategoryDetailView>getCategoryById(@PathVariable("id") long id){
		Optional<Category> categoryData = categoryService.getCategoryById(id);
		if(categoryData.isPresent()) {
			Category _category = categoryData.get();
			if(_category.isDeleted() == false) {
				CategoryDetailView _categoryDetail = categoryService.getCategoryDetailById(id);
				return new ResponseEntity<>(_categoryDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category newCategory){
		try{
			categoryService.createCategory(newCategory);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<Category>updateCategory(@PathVariable("id") long id,@RequestBody Category updatedCategory ){
		Optional<Category>categoryData = categoryService.getCategoryById(id);
		if(categoryData.isPresent()) {
			Category _category = categoryData.get();
			if(_category.isDeleted()==false) {
				categoryService.updateCategory(id, updatedCategory);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/category/{id}/delete")
	public ResponseEntity<Category>deleteCategory(@PathVariable("id") long id ){
		Optional<Category>categoryData = categoryService.getCategoryById(id);
		if(categoryData.isPresent()) {
			Category _category  = categoryData.get();
			if(_category.isDeleted() == false) {
				 categoryService.softDeleteCategory(id);
					return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
