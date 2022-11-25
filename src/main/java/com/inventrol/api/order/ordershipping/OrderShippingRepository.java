package com.inventrol.api.order.ordershipping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderShippingRepository extends JpaRepository<OrderShipping,Long> {

}
