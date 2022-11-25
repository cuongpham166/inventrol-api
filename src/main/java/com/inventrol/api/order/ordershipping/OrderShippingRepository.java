package com.inventrol.api.order.ordershipping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderShippingRepository extends JpaRepository<OrderShipping,Long> {
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
}
