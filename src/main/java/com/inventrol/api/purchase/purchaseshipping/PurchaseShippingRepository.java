package com.inventrol.api.purchase.purchaseshipping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseShippingRepository extends JpaRepository <PurchaseShipping,Long>{
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
}
