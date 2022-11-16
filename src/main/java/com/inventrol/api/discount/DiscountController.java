package com.inventrol.api.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.auth.MessageResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private DiscountRepository discountRepo;
	
	@GetMapping("/discount")
	public ResponseEntity<List<DiscountView>> getAllDiscounts() {
		try {
			List<DiscountView> discounts = new ArrayList<DiscountView>();
			discounts = discountService.getAllDiscounts();
			if (discounts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(discounts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/discount")
	public ResponseEntity<?> createDiscount(@RequestBody Discount newDiscount){
		try{						
			int newDiscountValue = newDiscount.getDiscountPercent();
			if(discountRepo.existsDiscountByDiscountPercent(newDiscount.getDiscountPercent())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This value already exists"));
			}
			
			if(newDiscountValue > 100) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This value must be equal or smaller than 100%"));
			}
			
			discountService.createDiscount(newDiscount);
			return ResponseEntity.ok().body(new MessageResponse("Success:  A new discount value has been created"));
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
