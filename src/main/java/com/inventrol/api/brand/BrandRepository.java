package com.inventrol.api.brand;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository <Brand,Long> {
	public List<Brand>findAllByOrderByIdAsc();
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);

}
