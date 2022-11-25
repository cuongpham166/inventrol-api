package com.inventrol.api.attributevalue;

import java.util.List;
import java.util.Optional;

public interface AttributeValueInterface {
	public Optional<AttributeValue> getAttributeValueById(long id);
	public List<AttributeValueView> getAllAttributeValues ();
	public List<AttributeValueView>searchAttributeValue (String name);
	public AttributeValueDetailView getAttributeValueDetailById(long id);
	public void createAttributeValue (AttributeValue newAttributeValue);
}
