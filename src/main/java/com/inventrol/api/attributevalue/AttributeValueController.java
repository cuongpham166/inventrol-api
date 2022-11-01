package com.inventrol.api.attributevalue;

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

import com.inventrol.api.atrribute.AttributeRepository;
import com.inventrol.api.auth.MessageResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AttributeValueController {

	@Autowired 
	private AttributeValueService attributeValueService;
	
	@Autowired
	private AttributeValueRepository attributeValueRepo;
	
	@Autowired
	private AttributeRepository attributeRepo;
	
	@GetMapping("/attribute-value")
	public ResponseEntity<List<AttributeValueView>> getAllAttributeValues(@RequestParam Optional<String> name) {
		try {
			List<AttributeValueView> attributevalues = new ArrayList<AttributeValueView>();
			if(name.isPresent()) {
				attributevalues = attributeValueService.searchAttributeValue(name.get());
			}else {
				attributevalues = attributeValueService.getAllAttributeValues();	
			}		
			if (attributevalues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(attributevalues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/attribute-value/{id}")
	public ResponseEntity<AttributeValueDetailView>getAttributeValueById(@PathVariable("id") long id){
		Optional<AttributeValue> attributeValueData = attributeValueService.getAttributeValueById(id);
		if(attributeValueData.isPresent()) {
			AttributeValue _attributeValue = attributeValueData.get();
			if(_attributeValue.isDeleted() == false) {
				AttributeValueDetailView _attributeValueDetail = attributeValueService.getAttributeValueDetailById(id);
				return new ResponseEntity<>(_attributeValueDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/attribute-value/{id}/products")
	public ResponseEntity<Set<AttributeValueDetailView.ProductData>>getProductsByAttributeValueId(@PathVariable("id") long id){
		Optional<AttributeValue> attributeValueData = attributeValueService.getAttributeValueById(id);
		if(attributeValueData.isPresent()) {
			AttributeValue _attributeValue = attributeValueData.get();
			if(_attributeValue.isDeleted() == false) {
				AttributeValueDetailView _attributeValueDetail = attributeValueService.getAttributeValueDetailById(id);
				Set<AttributeValueDetailView.ProductData> _products = new HashSet<AttributeValueDetailView.ProductData>();
				_products = _attributeValueDetail.getProduct();
				return new ResponseEntity<>(_products, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/attribute-value")
	public ResponseEntity<?> createAttributeValue(@RequestBody AttributeValue newAttributeValue){
		try{
			if(attributeValueRepo.existsAttributeValueByName(newAttributeValue.getName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
			}else {
				if(attributeRepo.existsAttributeByName(newAttributeValue.getAttribute().getName())) {
					attributeValueService.createAttributeValue(newAttributeValue);
					return ResponseEntity.ok().body(new MessageResponse("Success:  A new attribute value has been created"));
				}
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This attribute does not exist"));
			}
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  Internal Server Error"));
		}
	}
}
