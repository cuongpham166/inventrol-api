package com.inventrol.api.discount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long>{
	public List<Discount>findAllByOrderByIdAsc();
	
	public Boolean existsDiscountByDiscountPercent (int percent);
	public Optional<Discount>findByDiscountPercent (int percent);
	
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);
}
