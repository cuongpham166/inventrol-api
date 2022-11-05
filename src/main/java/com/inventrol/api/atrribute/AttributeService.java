package com.inventrol.api.atrribute;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.category.CategoryView;

@Service
public class AttributeService {
	
	@Autowired
	private AttributeRepository attributeRepo;
	
	public Optional<Attribute> getAttributeById(long id){
		Optional<Attribute>foundAttribute = attributeRepo.findById(id);
		return foundAttribute;
	}
	
	public List<AttributeView> getAllAttributes (){
		List<AttributeView>attributes = new ArrayList<AttributeView>();
		attributeRepo.findAllProjectedByOrderByIdAsc(AttributeView.class).forEach(attributes::add);
		List<AttributeView> result = attributes.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public List<AttributeValueOptionView> getAllAttributeValuesByAttribute (){
		List<AttributeValueOptionView>attributeValues = new ArrayList<AttributeValueOptionView>();
		attributeRepo.findAllProjectedByOrderByIdAsc(AttributeValueOptionView.class).forEach(attributeValues::add);
		List<AttributeValueOptionView> result = attributeValues.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public List<AttributeView>searchAttribute (String name){
		List<AttributeView>foundAttributes = new ArrayList<AttributeView>();
		attributeRepo.findProjectedByNameContainsIgnoreCase(name, AttributeView.class).forEach(foundAttributes::add);
		List<AttributeView> result = foundAttributes.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public AttributeDetailView getAttributeDetailById(long id) {
		AttributeDetailView attributeDetail = attributeRepo.findProjectedById(id, AttributeDetailView.class);
		return attributeDetail;
	}
	
	public void createAttribute (Attribute newAttribute) {
		attributeRepo.save(newAttribute);
	}
	
}
