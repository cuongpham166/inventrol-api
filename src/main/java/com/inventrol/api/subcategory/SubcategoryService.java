package com.inventrol.api.subcategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.category.CategoryService;
import com.inventrol.api.category.Category;

@Service
public class SubcategoryService {

	@Autowired
	private SubcategoryRepository subcategoryRepo;
	
	@Autowired
	private CategoryService categoryService;
	
	public List<SubcategoryView> getAllSubcategories () {
		List<SubcategoryView> subcategories = new ArrayList<SubcategoryView>();
		subcategoryRepo.findAllProjectedByOrderByIdAsc(SubcategoryView.class).forEach(subcategories::add);
		return subcategories;
	}
	
	public Subcategory createSubcategory(Subcategory newSubcategory) {
		newSubcategory.setCreatedDate(LocalDate.now());
		long categoryId = newSubcategory.getCategory().getId();
		Optional<Category>categoryData = categoryService.getCategoryById(categoryId);
		if(categoryData.isPresent()) {
			Category foundCategory = categoryData.get(); 
			newSubcategory.setCategory(foundCategory);
			subcategoryRepo.save(newSubcategory);
			return newSubcategory;
		}else {
			return null;
		}
	}
}
