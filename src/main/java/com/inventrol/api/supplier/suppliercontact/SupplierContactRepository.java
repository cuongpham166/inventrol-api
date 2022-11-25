package com.inventrol.api.supplier.suppliercontact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierContactRepository extends JpaRepository<SupplierContact, Long> {
	public List<SupplierContact> findAllByOrderByIdAsc();
}
