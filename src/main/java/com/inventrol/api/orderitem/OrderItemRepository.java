package com.inventrol.api.orderitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository <Long,OrderItem>{
	public List<OrderItem> findAllByOrderByIdAsc();
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);
	public <T> List<T> findProjectedByNameContainsIgnoreCase(String name, Class<T> type);
}
