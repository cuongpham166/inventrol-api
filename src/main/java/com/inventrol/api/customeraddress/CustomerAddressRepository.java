package com.inventrol.api.customeraddress;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long>{
	public List<CustomerAddress>findAllByOrderByIdAsc();
}
