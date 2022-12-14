package com.inventrol.api.subcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.category.Category;
import com.inventrol.api.category.CategoryRepository;

@Service
public class SubcategoryService implements SubcategoryInterface {

	@Autowired
	private SubcategoryRepository subcategoryRepo;
		
	@Autowired
	private CategoryRepository categoryRepo;
	
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
	
	public List<SubcategoryView>searchSubcategory (String name){
		List<SubcategoryView>foundSubcategories = new ArrayList<SubcategoryView>();
		subcategoryRepo.findProjectedByNameContainsIgnoreCase(name, SubcategoryView.class).forEach(foundSubcategories::add);
		List<SubcategoryView> result = foundSubcategories.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
		
	public void createSubcategory(Subcategory newSubcategory) {
		String categoryName = newSubcategory.getCategory().getName();
		Optional<Category>categoryData = categoryRepo.findByName(categoryName);
		if(categoryData.isPresent()) {
			Category foundCategory = categoryData.get(); 
			newSubcategory.setCategory(foundCategory);
			subcategoryRepo.save(newSubcategory);
		}
	}
	
	public void updateSubcategory (long id, Subcategory updatedSubcategory) {
		Optional<Subcategory>subcategoryData = getSubcategoryById(id);
		String categoryName = updatedSubcategory.getCategory().getName();
		Optional<Category>categoryData = categoryRepo.findByName(categoryName);
		if(subcategoryData.isPresent() && categoryData.isPresent()) {
			Subcategory _subcategory = subcategoryData.get();
			_subcategory.setName(updatedSubcategory.getName());
			_subcategory.setNotice(updatedSubcategory.getNotice());
			_subcategory.setTagColor(updatedSubcategory.getTagColor());
			_subcategory.setCategory(categoryData.get());
			subcategoryRepo.save(_subcategory);
		}
	}
}
