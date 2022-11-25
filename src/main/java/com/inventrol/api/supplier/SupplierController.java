package com.inventrol.api.supplier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.auth.MessageResponse;
import com.inventrol.api.product.Product;
import com.inventrol.api.purchase.Purchase;
import com.inventrol.api.purchase.purchaseitem.PurchaseItem;
import com.inventrol.api.supplier.suppliercontact.SupplierContact;
import com.inventrol.api.supplier.suppliercontact.SupplierContactRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SupplierController {

	@Autowired 
	private SupplierService supplierService;
	
	@Autowired
	private SupplierRepository supplierRepo;
	
	@Autowired
	private SupplierContactRepository contactRepo;
	
	@GetMapping("/supplier")
	public ResponseEntity<List<SupplierView>> getAllSuppliers(@RequestParam Optional<String> name) {
		try {
			List<SupplierView> suppliers = new ArrayList<SupplierView>();
			if(name.isPresent()) {
				suppliers = supplierService.searchSupplier(name.get());
			}else {
				suppliers = supplierService.getAllSuppliers();
			}		
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
			Supplier _supplier = supplierData.get();
			if(_supplier.isDeleted() == false) {
				SupplierDetailView _supplierDetail = supplierService.getSupplierDetailById(id);
				return new ResponseEntity<>(_supplierDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/supplier/{id}/products")
	public ResponseEntity<Set<SupplierDetailView.ProductData>> getProductsBySupplierId(@PathVariable("id") long id){
		Optional<Supplier> supplierData = supplierService.getSupplierById(id);
		if(supplierData.isPresent()) {
			Supplier _supplier = supplierData.get();
			if(_supplier.isDeleted() == false) {
				SupplierDetailView _supplierDetail = supplierService.getSupplierDetailById(id);
				Set<SupplierDetailView.ProductData> _products = new HashSet<SupplierDetailView.ProductData>();
				_products =	_supplierDetail.getProduct();
				return new ResponseEntity<>(_products, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/supplier")
	public ResponseEntity<?> createSupplier(@RequestBody Supplier newSupplier){
		try{
			if(supplierRepo.existsSupplierByName(newSupplier.getName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
			}
			supplierService.createSupplier(newSupplier);
			return ResponseEntity.ok().body(new MessageResponse("Success:  A new supplier has been created"));
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/supplier/{supplierId}")
	public ResponseEntity<?>updateSuppleir(@PathVariable("supplierId")long supplierId, @RequestBody Supplier updatedSupplier){
		Optional<Supplier>supplierData = supplierService.getSupplierById(supplierId);
		if(supplierData.isPresent()) {
			Supplier _supplier = supplierData.get();
			if(_supplier.isDeleted() == false) {
				supplierService.updateSupplier(supplierId, updatedSupplier);
				return ResponseEntity.ok().body(new MessageResponse("Success: Supplier has been updated"));
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/supplier/{supplierId}/contact/{contactId}")
	public ResponseEntity<?>updateSupplierContact(@PathVariable("supplierId")long supplierId, @PathVariable("contactId")long contactId ,@RequestBody SupplierContact updatedContact){
		Optional<Supplier>supplierData = supplierService.getSupplierById(supplierId);
		Optional<SupplierContact>contactData = contactRepo.findById(contactId);
		if (supplierData.isPresent() && contactData.isPresent()) {
			Supplier _supplier = supplierData.get();
			SupplierContact _contact = contactData.get();
			if(_supplier.isDeleted() == false && _contact.isDeleted() == false && _contact.getSupplier().getId() == supplierId) {
				supplierService.updateSupplierContact(supplierId, contactId, updatedContact);
				return ResponseEntity.ok().body(new MessageResponse("Success: Contact has been updated"));
			}
			return ResponseEntity.notFound().build();
			
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/supplier/{supplierId}/purchase/add")
	public ResponseEntity<Set<SupplierDetailView.ProductData>> createNewPurchase(@PathVariable("supplierId") long supplierId){
		Optional<Supplier> supplierData = supplierService.getSupplierById(supplierId);
		if(supplierData.isPresent()) {
			Supplier _supplier = supplierData.get();
			if(_supplier.isDeleted() == false) {
				SupplierDetailView _supplierDetail = supplierService.getSupplierDetailById(supplierId);
				Set<SupplierDetailView.ProductData> _products = new HashSet<SupplierDetailView.ProductData>();
				_products =	_supplierDetail.getProduct();
				return new ResponseEntity<>(_products, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	@PostMapping("/supplier/{supplierId}/purchase/add")
	public ResponseEntity<?> saveNewPurchase(@PathVariable("supplierId") long supplierId, @RequestBody Purchase newPurchase){
		try{
			Optional <Supplier>supplierData = supplierRepo.findById(supplierId);
			if(supplierData.isPresent()) {
				supplierService.saveNewPurchase(supplierId, newPurchase);
				return ResponseEntity.ok().body(new MessageResponse("Success:  A new purchase has been made"));
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
		}

	}

	
}
