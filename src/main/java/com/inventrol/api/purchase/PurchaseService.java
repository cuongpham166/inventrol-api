package com.inventrol.api.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
	@Autowired PurchaseRepository purchaseRepo;
	
	public Optional<Purchase> getPurchaseById(long id){
		Optional<Purchase> foundPurchase = purchaseRepo.findById(id);
		return foundPurchase;
	}
	
	public PurchaseDetailView getPurchaseDetailById(long id) {
		PurchaseDetailView purchaseDetail = purchaseRepo.findProjectedById(id, PurchaseDetailView.class);
		return purchaseDetail;
	}
	
	public List<PurchaseView> getAllPurchases (){
		List<PurchaseView>purchases = new ArrayList<PurchaseView>();
		purchaseRepo.findAllProjectedByOrderByIdAsc(PurchaseView.class).forEach(purchases::add);
		List<PurchaseView> results = purchases.stream().filter(res -> res.isDeleted() == false).collect(Collectors.toList());
		return results;
	}
}
