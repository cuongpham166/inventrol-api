package com.inventrol.api.subcategory;

import java.util.List;
import java.util.Optional;

public interface SubcategoryInterface {
	public Optional<Subcategory> getSubcategoryById(long id);
	public SubcategoryDetailView getSubcategoryDetailById(long id);
	public List<SubcategoryView> getAllSubcategories ();
	public List<SubcategoryView>searchSubcategory (String name);
	public void createSubcategory(Subcategory newSubcategory);
	public void updateSubcategory (long id, Subcategory updatedSubcategory);
}
