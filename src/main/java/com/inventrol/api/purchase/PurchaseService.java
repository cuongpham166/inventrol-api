package com.inventrol.api.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.purchase.purchasehistory.PurchaseHistory;
import com.inventrol.api.purchase.purchasehistory.PurchaseHistoryRepository;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShipping;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShippingRepository;

@Service
public class PurchaseService implements PurchaseInterface{
	@Autowired 
	private PurchaseRepository purchaseRepo;
	
	@Autowired
	private PurchaseHistoryRepository purchasehistoryRepo;
	
	@Autowired
	private PurchaseShippingRepository purchaseshippingRepo;
	
	public Optional<Purchase> getPurchaseById(long id){
		Optional<Purchase> purchaseData = purchaseRepo.findById(id);
		return purchaseData;
	}
	
	public Optional<PurchaseShipping>getPurchaseShippingById(long id){
		Optional<PurchaseShipping>purchaseShippingData = purchaseshippingRepo.findById(id);
		return purchaseShippingData;
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
	
	
	public void createPurchaseHistory (String historyStatus, Purchase foundPurchase) {
		PurchaseHistory newPurchaseHistory = new PurchaseHistory();
		newPurchaseHistory.setStatus(historyStatus);
		newPurchaseHistory.setPurchase(foundPurchase);
		foundPurchase.getPurchasehistory().add(newPurchaseHistory);
		purchasehistoryRepo.save(newPurchaseHistory);
	}
	
	public void updatePurchaseShipping (long purchaseId, long purchaseShippingId, PurchaseShipping updatedPurchaseShipping) {
		Purchase foundPurchase = getPurchaseById(purchaseId).get();
		PurchaseShipping foundPurchaseShipping = getPurchaseShippingById(purchaseShippingId).get();
		String updatedPurchaseShippingStatus = updatedPurchaseShipping.getStatus();

		/*switch(updatedPurchaseShippingStatus) {
			case "Shipped":
				createPurchaseHistory("Purchase shipped",foundPurchase);
				break;
			case "Delivered":
				createPurchaseHistory("Purchase delivered",foundPurchase);
				break;
			case "Checking":
				createPurchaseHistory("Purchase checking",foundPurchase);
				break;
			case "Completed":
				createPurchaseHistory("Purchase completed",foundPurchase);
				break;
			case "Returned":
				createPurchaseHistory("Purchase returned",foundPurchase);
				break;
			case "Cancelled":
				createPurchaseHistory("Purchase cancelled",foundPurchase);
				break;
			default:
				break;
		}*/		
		
		if (updatedPurchaseShippingStatus != "Processing") {
			createPurchaseHistory(updatedPurchaseShippingStatus,foundPurchase);
		}
		
		foundPurchaseShipping.setNotice(updatedPurchaseShipping.getNotice());
		foundPurchaseShipping.setService(updatedPurchaseShipping.getService());
		foundPurchaseShipping.setPurchase(foundPurchase);
		foundPurchaseShipping.setStatus(updatedPurchaseShippingStatus);
		foundPurchaseShipping.setTrackingNumber(updatedPurchaseShipping.getTrackingNumber());
		foundPurchase.setPurchaseshipping(foundPurchaseShipping);
		purchaseshippingRepo.save(foundPurchaseShipping);
	}
	

}
