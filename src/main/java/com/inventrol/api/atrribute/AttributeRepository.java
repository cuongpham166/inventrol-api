package com.inventrol.api.atrribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository <Attribute,Long> {
	public List<Attribute>findAllByOrderByIdAsc();
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);
	public <T> List<T> findProjectedByNameContainsIgnoreCase(String name, Class<T> type);
}
