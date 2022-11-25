package com.inventrol.api.discount;

import java.util.List;
import java.util.Optional;

public interface DiscountInterface {
	public Optional<Discount> getDiscountById(long id);
	public List<DiscountView> getAllDiscounts ();
	public void createDiscount (Discount newDiscount);
}
