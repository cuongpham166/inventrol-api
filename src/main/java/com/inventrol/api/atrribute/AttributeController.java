package com.inventrol.api.atrribute;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AttributeController {

	@Autowired
	private AttributeService attributeService;
	
	@GetMapping("/attribute")
	public ResponseEntity<List<AttributeView>> getAllAttributes(@RequestParam Optional<String> name) {
		try {
			List<AttributeView> attributes = new ArrayList<AttributeView>();
			if(name.isPresent()) {
				attributes = attributeService.searchAttribute(name.get());
			}else {
				attributes = attributeService.getAllAttributes();
			}
			if (attributes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(attributes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/attribute/{id}")
	public ResponseEntity<AttributeDetailView>getAttributeById(@PathVariable("id") long id){
		Optional<Attribute> attributeData = attributeService.getAttributeById(id);
		if(attributeData.isPresent()) {
			Attribute _attribute = attributeData.get();
			if(_attribute.isDeleted() == false) {
				AttributeDetailView _attributeDetail = attributeService.getAttributeDetailById(id);
				return new ResponseEntity<>(_attributeDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/attribute")
	public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute newAttribute){
		try{
			attributeService.createAttribute(newAttribute);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
