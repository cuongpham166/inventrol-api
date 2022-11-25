package com.inventrol.api.purchase.purchaseitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository <PurchaseItem,Long> {
	public List<PurchaseItem>findAllByOrderByIdAsc();
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);
}
