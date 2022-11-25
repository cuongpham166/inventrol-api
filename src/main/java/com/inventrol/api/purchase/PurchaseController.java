package com.inventrol.api.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.auth.MessageResponse;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShipping;
import com.inventrol.api.subcategory.Subcategory;
import com.inventrol.api.subcategory.SubcategoryDetailView;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping("/purchase")
	public ResponseEntity<List<PurchaseView>> getAllPurchases() {
		try {
			List<PurchaseView> purchases = new ArrayList<PurchaseView>();
			purchases = purchaseService.getAllPurchases();
			if (purchases.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(purchases, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/purchase/{id}")
	public ResponseEntity<PurchaseDetailView>getPurchaseById(@PathVariable("id") long id){
		Optional<Purchase> purchaseData = purchaseService.getPurchaseById(id);
		if(purchaseData.isPresent()) {
			Purchase _purchase = purchaseData.get();
			if(_purchase.isDeleted() == false) {
				PurchaseDetailView _purchaseDetail = purchaseService.getPurchaseDetailById(id);
				return new ResponseEntity<>(_purchaseDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/purchase/{purchaseId}/shipping/{shippingId}")
	public ResponseEntity<?>updatePurchaseShipping(@PathVariable("purchaseId")long purchaseId, @PathVariable("shippingId")long shippingId, @RequestBody PurchaseShipping updatedPurchaseShipping){
		try{
			Optional<Purchase>purchaseData = purchaseService.getPurchaseById(purchaseId);
			Optional<PurchaseShipping>purchaseShippingData = purchaseService.getPurchaseShippingById(shippingId);
			if(purchaseData.isPresent() && purchaseShippingData.isPresent()) {
				Purchase foundPurchase = purchaseData.get();
				if(foundPurchase.getPurchaseshipping().getId() == shippingId) {
					purchaseService.updatePurchaseShipping(purchaseId, shippingId, updatedPurchaseShipping);
					return ResponseEntity.ok().body(new MessageResponse("Success: Purchase's shipping has been updated"));
				}
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  "+ e.getMessage()));
		}
		
	}
}
