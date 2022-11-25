package com.inventrol.api.customer.customeraddress;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long>{
	public List<CustomerAddress>findAllByOrderByIdAsc();
	public List<CustomerAddress>findAllByCustomerId(long customerId);
	//public Optional<CustomerAddress>findByCustomerId(long customerId);
	
	public <T> T findProjectedById(long id, Class<T> type);
}
