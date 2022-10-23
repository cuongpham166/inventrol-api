package com.inventrol.api.discount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiscountService {

	@Autowired
	private DiscountRepository discountRepo;
	
	public Optional<Discount> getDiscountById(long id){
		Optional<Discount>foundDiscount = discountRepo.findById(id);
		return foundDiscount;
	}
	
	public List<DiscountView> getAllDiscounts (){
		List<DiscountView>discounts = new ArrayList<DiscountView>();
		discountRepo.findAllProjectedByOrderByIdAsc(DiscountView.class).forEach(discounts::add);
		List<DiscountView> result = discounts.stream().filter(dis -> dis.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public void createDiscount (Discount newDiscount) {
		newDiscount.setCreatedDate(LocalDate.now());
		discountRepo.save(newDiscount);
	}
	
}
