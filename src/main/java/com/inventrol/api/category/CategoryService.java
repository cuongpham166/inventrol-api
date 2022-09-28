package com.inventrol.api.category;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Optional<Category> getCategoryById(long id){
		Optional<Category>foundCategory = categoryRepo.findById(id);
		return foundCategory;
	}
	
	public List<CategoryView> getAllCategories (){
		List<CategoryView>categories = new ArrayList<CategoryView>();
		categoryRepo.findAllProjectedByOrderByIdAsc(CategoryView.class).forEach(categories::add);
		return categories;
	}
	
	public Category createCategory (Category newCategory) {
		newCategory.setCreatedDate(LocalDate.now());
		Category savedCategory = categoryRepo.save(newCategory);
		return savedCategory;
	}
	
	public Category updateCategory (long id, Category updatedCategory) {
		Optional<Category>categoryData = getCategoryById(id);
		Category _category = categoryData.get();
		_category.setName(updatedCategory.getName());
		_category.setNotice(updatedCategory.getNotice());
		_category.setUpdatedDate(LocalDate.now());
		Category savedCategory = categoryRepo.save(_category);
		return savedCategory;
	}
	
	public Category softDeleteCategory(long id){
		Optional<Category>categoryData = getCategoryById(id);
		Category _category = categoryData.get();
		_category.setDeleted(true);
		Category savedCategory = categoryRepo.save(_category);
		return savedCategory;
	}

}
