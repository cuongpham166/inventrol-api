package com.inventrol.api.attributevalue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.atrribute.Attribute;
import com.inventrol.api.atrribute.AttributeService;
import com.inventrol.api.atrribute.AttributeView;

@Service
public class AttributeValueService {
	@Autowired
	private AttributeValueRepository attributeValueRepo;
	
	@Autowired
	private AttributeService attributeService;
	
	public Optional<AttributeValue> getAttributeValueById(long id){
		Optional<AttributeValue>foundAttributeValue = attributeValueRepo.findById(id);
		return foundAttributeValue;
	}
	
	public List<AttributeValueView> getAllAttributeValues (){
		List<AttributeValueView>attributeValues = new ArrayList<AttributeValueView>();
		attributeValueRepo.findAllProjectedByOrderByIdAsc(AttributeValueView.class).forEach(attributeValues::add);
		List<AttributeValueView> result = attributeValues.stream().filter(res -> res.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public List<AttributeValueView>searchAttributeValue (String name){
		List<AttributeValueView>foundAttributeValues = new ArrayList<AttributeValueView>();
		attributeValueRepo.findProjectedByNameContainsIgnoreCase(name, AttributeValueView.class).forEach(foundAttributeValues::add);
		List<AttributeValueView> result = foundAttributeValues.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public AttributeValueDetailView getAttributeValueDetailById(long id) {
		AttributeValueDetailView attributeValueDetail = attributeValueRepo.findProjectedById(id, AttributeValueDetailView.class);
		return attributeValueDetail;
	}
	
	public void createAttributeValue (AttributeValue newAttributeValue) {
		newAttributeValue.setCreatedDate(LocalDate.now());
		long attributeValueId = newAttributeValue.getAttribute().getId();
		Optional<Attribute>attributeData = attributeService.getAttributeById(attributeValueId);
		if(attributeData.isPresent()) {
			Attribute foundAttribute = attributeData.get();
			newAttributeValue.setAttribute(foundAttribute);
			attributeValueRepo.save(newAttributeValue);
		}
	}
}
