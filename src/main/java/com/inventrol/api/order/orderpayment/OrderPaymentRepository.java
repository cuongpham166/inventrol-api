package com.inventrol.api.order.orderpayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository <OrderPayment,Long> {
	public List<OrderPayment>findAllByOrderByIdAsc();
	public <T> List<T> findAllProjectedByOrderByIdAsc(Class<T> type);
	public <T> T findProjectedById(long id, Class<T> type);

}
