package com.inventrol.api.attributevalue;

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
public class AttributeValueController {

	@Autowired 
	private AttributeValueService attributeValueService;
	
	
	@GetMapping("/attribute-value")
	public ResponseEntity<List<AttributeValueView>> getAllAttributeValues() {
		try {
			List<AttributeValueView> attributevalues = attributeValueService.getAllAttributeValues();
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
	
	@PostMapping("/attribute-value")
	public ResponseEntity<AttributeValue> createAttributeValue(@RequestBody AttributeValue newAttributeValue){
		try{
			attributeValueService.createAttributeValue(newAttributeValue);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
