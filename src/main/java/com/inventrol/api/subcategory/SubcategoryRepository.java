package com.inventrol.api.subcategory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository <Subcategory,Long>{
	public List<Subcategory> findAllByOrderByIdAsc();
	
	public Boolean existsSubcategoryByName (String name);
	public Optional<Subcategory>findByName (String name);
	
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);
	public <T> List<T> findProjectedByNameContainsIgnoreCase(String name, Class<T> type);
}


