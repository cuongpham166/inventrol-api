package com.inventrol.api.atrribute;

import java.util.List;
import java.util.Optional;

public interface AttributeInterface {
	public Optional<Attribute> getAttributeById(long id);
	public List<AttributeView> getAllAttributes ();
	public List<AttributeValueOptionView> getAllAttributeValuesByAttribute ();
	public List<AttributeView>searchAttribute (String name);
	public AttributeDetailView getAttributeDetailById(long id);
	public void createAttribute (Attribute newAttribute);
}
