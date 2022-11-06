package com.inventrol.api.category;

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


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private  CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepo;
		
	@GetMapping("/category")
	public ResponseEntity<List<CategoryView>> getAllCategories(@RequestParam Optional<String> name) {
		try {
			List<CategoryView> categories = new ArrayList<CategoryView>();
			if(name.isPresent()) {
				categories = categoryService.searchCategory(name.get());
			}else {
				categories = categoryService.getAllCategories();
			}
			if (categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categories, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/category/subcategories")
	public ResponseEntity<List<SubcategoryOptionView>> getAllSubcategoriesByCategory() {
		try {
			List<SubcategoryOptionView>subcategories = new ArrayList<SubcategoryOptionView>();
			subcategories = categoryService.getAllSubcategoriesByCategory();
			if(subcategories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(subcategories, HttpStatus.OK);
		}catch (Exception e) {
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
	public ResponseEntity<?> createCategory(@RequestBody Category newCategory){
		try{
			if(categoryRepo.existsCategoryByName(newCategory.getName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
			}
			if(categoryRepo.existsCategoryByTagColor(newCategory.getTagColor())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This color is already in use"));
			}
			categoryService.createCategory(newCategory);
			return ResponseEntity.ok().body(new MessageResponse("Success:  A new category has been created"));
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  Internal Server Error"));
		}
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<?>updateCategory(@PathVariable("id") long id,@RequestBody Category updatedCategory ){
		Optional<Category>categoryData = categoryService.getCategoryById(id);
		if(categoryData.isPresent()) {
			Category _category = categoryData.get();
			if(_category.isDeleted() == false) {
				categoryService.updateCategory(id, updatedCategory);
				return ResponseEntity.ok().body(new MessageResponse("Success: Category has been updated"));
			}else {
				return ResponseEntity.notFound().build();
				//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}else {
			return ResponseEntity.notFound().build();
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/category/{id}/delete")
	public ResponseEntity<?>deleteCategory(@PathVariable("id") long id ){
		Optional<Category>categoryData = categoryService.getCategoryById(id);
		if(categoryData.isPresent()) {
			Category _category = categoryData.get();
			if(_category.isDeleted() == false) {
				 categoryService.softDeleteCategory(id);
				 return ResponseEntity.ok().body(new MessageResponse("Success: Category has been deleted"));
			}else {
				return ResponseEntity.notFound().build();
				//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return ResponseEntity.notFound().build();
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
