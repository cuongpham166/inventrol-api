package com.inventrol.api.supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SupplierController {

	@Autowired 
	private SupplierService supplierService;
	

	@GetMapping("/supplier")
	public ResponseEntity<List<SupplierView>> getAllSuppliers() {
		try {
			List<SupplierView> suppliers = supplierService.getAllSuppliers();
			if (suppliers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(suppliers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/supplier/{id}")
	public ResponseEntity<SupplierDetailView> getSupplierById(@PathVariable("id") long id){
		Optional<Supplier> supplierData = supplierService.getSupplierById(id);
		if(supplierData.isPresent()) {
			SupplierDetailView _supplierDetail = supplierService.getSupplierDetailById(id);
			return new ResponseEntity<>(_supplierDetail, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/supplier")
	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier newSupplier){
		try{
			supplierService.createSupplier(newSupplier);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
