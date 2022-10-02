package com.inventrol.api.atrribute;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public AttributeDetailView getAttributeDetailById(long id) {
		AttributeDetailView attributeDetail = attributeRepo.findProjectedById(id, AttributeDetailView.class);
		return attributeDetail;
	}
	
	public void createAttribute (Attribute newAttribute) {
		newAttribute.setCreatedDate(LocalDate.now());
		attributeRepo.save(newAttribute);
	}
	
}
