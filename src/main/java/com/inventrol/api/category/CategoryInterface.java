package com.inventrol.api.category;

import java.util.List;
import java.util.Optional;

public interface CategoryInterface {
	public Optional<Category> getCategoryById(long id);
	public List<CategoryView> getAllCategories ();
	public List<SubcategoryOptionView> getAllSubcategoriesByCategory ();
	public CategoryDetailView getCategoryDetailById(long id);
	public List<CategoryView>searchCategory (String name);
	public Category createCategory (Category newCategory);
	public Category updateCategory (long id, Category updatedCategory);
	public Category softDeleteCategory(long id);
}
