package com.inventrol.api.supplier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.category.Category;
import com.inventrol.api.category.CategoryDetailView;
import com.inventrol.api.contact.Contact;
import com.inventrol.api.contact.ContactRepository;

@Service
public class SupplierService {
	
	@Autowired 
	private SupplierRepository supplierRepo;
	
	@Autowired
	private ContactRepository contactRepo;
	
	public Optional<Supplier> getSupplierById(long id){
		Optional<Supplier>foundSupplier = supplierRepo.findById(id);
		return foundSupplier;
	}
	
	public List<SupplierView>getAllSuppliers(){
		List<SupplierView> suppliers = new ArrayList<SupplierView>();
		supplierRepo.findAllProjectedByOrderByIdAsc(SupplierView.class).forEach(suppliers::add);
		List<SupplierView> result = suppliers.stream().filter(sup -> sup.isDeleted()==false).collect(Collectors.toList());
		return result;
	}
	
	public SupplierDetailView getSupplierDetailById(long id) {
		SupplierDetailView supplierDetail = supplierRepo.findProjectedById(id, SupplierDetailView.class);
		return supplierDetail;
	}
	
	public void createSupplier(Supplier newSupplier) {
		newSupplier.setCreatedDate(LocalDate.now());
		Contact newContact = newSupplier.getContact();
		newContact.setSupplier(newSupplier);
		newSupplier.setContact(newContact);
		contactRepo.save(newContact);
	}
}
