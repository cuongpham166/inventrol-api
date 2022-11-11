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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.auth.MessageResponse;
import com.inventrol.api.product.Product;
import com.inventrol.api.purchase.Purchase;
import com.inventrol.api.purchaseitem.PurchaseItem;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SupplierController {

	@Autowired 
	private SupplierService supplierService;
	
	@Autowired
	private SupplierRepository supplierRepo;
	
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
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  Internal Server Error"));
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
	public ResponseEntity<BigDecimal> saveNewPurchase(@PathVariable("supplierId") long supplierId, @RequestBody Purchase newPurchase){
		Optional <Supplier>supplierData = supplierRepo.findById(supplierId);
		if(supplierData.isPresent()) {
			BigDecimal totalCost = supplierService.createPurchase(supplierId, newPurchase);
			return new ResponseEntity<>(totalCost, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/supplier/{supplierId}/purchase/add-test")
	public ResponseEntity<BigDecimal> createPurchaseTest(@PathVariable("supplierId") long supplierId, @RequestBody Purchase newPurchase){
		Optional <Supplier>supplierData = supplierRepo.findById(supplierId);
		if(supplierData.isPresent()) {
			BigDecimal totalCost = supplierService.createPurchaseTestNew(supplierId, newPurchase);
			return new ResponseEntity<>(totalCost, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
