package com.inventrol.api.contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	public List<Contact> findAllByOrderByIdAsc();
}
