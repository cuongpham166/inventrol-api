package com.inventrol.api.purchase;

import java.util.List;
import java.util.Optional;

import com.inventrol.api.purchase.purchaseshipping.PurchaseShipping;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShippingView;

public interface PurchaseInterface {
	public Optional<Purchase> getPurchaseById(long id);
	public Optional<PurchaseShipping>getPurchaseShippingById(long id);
	
	public PurchaseDetailView getPurchaseDetailById(long id);
	
	public List<PurchaseView> getAllPurchases ();
	public List<PurchaseShippingView>getAllPurchaseShipping();
	
	public void createPurchaseHistory (String historyStatus, Purchase foundPurchase);
	public void updatePurchaseShipping (long purchaseId, long purchaseShippingId, PurchaseShipping updatedPurchaseShipping);
}
