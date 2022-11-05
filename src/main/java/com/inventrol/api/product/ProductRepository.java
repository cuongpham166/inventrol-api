package com.inventrol.api.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
	List<Product>findAllByOrderByIdAsc();
	
	public Boolean existsProductByName (String name);	
	public Optional<Product>findByName (String name);
	
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);
	public <T> List<T> findProjectedByNameContainsIgnoreCase(String name, Class<T> type);
}
