package com.inventrol.api.category;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> getAllCategories (){
		List<Category>categories = new ArrayList<Category>();
		categoryRepo.findAll().forEach(categories::add);
		return categories;
	}
	
	public Category createCategory (Category newCategory) {
		newCategory.setCreatedDate(LocalDate.now());
		return categoryRepo.save(newCategory);
	}
}
