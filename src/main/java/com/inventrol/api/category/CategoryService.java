package com.inventrol.api.category;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		List<CategoryView> result = categories.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public CategoryDetailView getCategoryDetailById(long id) {
		CategoryDetailView categoryDetail = categoryRepo.findProjectedById(id, CategoryDetailView.class);
		return categoryDetail;
	}
	
	public List<CategoryView>searchCategory (String name){
		List<CategoryView>foundCategories = new ArrayList<CategoryView>();
		categoryRepo.findProjectedByNameContainsIgnoreCase(name, CategoryView.class).forEach(foundCategories::add);
		List<CategoryView> result = foundCategories.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public Category createCategory (Category newCategory) {
		Category savedCategory = categoryRepo.save(newCategory);
		return savedCategory;
	}
	
	public Category updateCategory (long id, Category updatedCategory) {
		Optional<Category>categoryData = getCategoryById(id);
		Category _category = categoryData.get();
		_category.setName(updatedCategory.getName());
		_category.setNotice(updatedCategory.getNotice());
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
