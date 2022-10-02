package com.inventrol.api.subcategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public Optional<Subcategory> getSubcategoryById(long id){
		Optional<Subcategory> foundSubcategory = subcategoryRepo.findById(id);
		return foundSubcategory;
	}
	
	public SubcategoryDetailView getSubcategoryDetailById(long id) {
		SubcategoryDetailView subcategoryDetail = subcategoryRepo.findProjectedById(id, SubcategoryDetailView.class);
		return subcategoryDetail;
	}
	
	public List<SubcategoryView> getAllSubcategories () {
		List<SubcategoryView> subcategories = new ArrayList<SubcategoryView>();
		subcategoryRepo.findAllProjectedByOrderByIdAsc(SubcategoryView.class).forEach(subcategories::add);
		List <SubcategoryView> result = subcategories.stream().filter(subcat -> subcat.isDeleted()==false).collect(Collectors.toList());
		return result;
	}
	
	public void createSubcategory(Subcategory newSubcategory) {
		newSubcategory.setCreatedDate(LocalDate.now());
		long categoryId = newSubcategory.getCategory().getId();
		Optional<Category>categoryData = categoryService.getCategoryById(categoryId);
		if(categoryData.isPresent()) {
			Category foundCategory = categoryData.get(); 
			newSubcategory.setCategory(foundCategory);
			subcategoryRepo.save(newSubcategory);
		}
	}
	
	public void updateSubcategory (long id, Subcategory updatedSubcategory) {
		Optional<Subcategory>subcategoryData = getSubcategoryById(id);
		long categoryId = updatedSubcategory.getCategory().getId();
		Optional<Category>categoryData = categoryService.getCategoryById(categoryId);
		if(subcategoryData.isPresent() && categoryData.isPresent()) {
			Subcategory _subcategory = subcategoryData.get();
			_subcategory.setName(updatedSubcategory.getName());
			_subcategory.setNotice(updatedSubcategory.getNotice());
			_subcategory.setUpdatedDate(LocalDate.now());
			_subcategory.setCategory(categoryData.get());
			subcategoryRepo.save(_subcategory);
		}
	}
}
