package com.inventrol.api.supplier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.product.Product;
import com.inventrol.api.product.ProductRepository;
import com.inventrol.api.purchase.Purchase;
import com.inventrol.api.purchase.PurchaseRepository;
import com.inventrol.api.purchase.purchasehistory.PurchaseHistory;
import com.inventrol.api.purchase.purchasehistory.PurchaseHistoryRepository;
import com.inventrol.api.purchase.purchaseitem.PurchaseItem;
import com.inventrol.api.purchase.purchaseitem.PurchaseItemRepository;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShipping;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShippingRepository;
import com.inventrol.api.supplier.suppliercontact.SupplierContact;
import com.inventrol.api.supplier.suppliercontact.SupplierContactRepository;

@Service
public class SupplierService implements SupplierInterface {
	
	private BigDecimal totalCost = BigDecimal.ZERO;

	@Autowired 
	private SupplierRepository supplierRepo;
	
	@Autowired
	private SupplierContactRepository contactRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private PurchaseRepository purchaseRepo;
	
	@Autowired
	private PurchaseHistoryRepository purchasehistoryRepo;
	
	@Autowired
	private PurchaseShippingRepository purchaseshippingRepo;
	
	@Autowired
	private PurchaseItemRepository purchaseitemRepo;
	
	public Optional<Supplier> getSupplierById(long id){
		Optional<Supplier>foundSupplier = supplierRepo.findById(id);
		return foundSupplier;
	}
	
	public List<SupplierView>getAllSuppliers(){
		List<SupplierView> suppliers = new ArrayList<SupplierView>();
		supplierRepo.findAllProjectedByOrderByIdAsc(SupplierView.class).forEach(suppliers::add);
		List<SupplierView> result = suppliers.stream().filter(sup -> sup.isDeleted()==false).collect(Collectors.toList());
		return result;
	}
	
	public List<SupplierView>searchSupplier (String name){
		List<SupplierView>foundSuppliers = new ArrayList<SupplierView>();
		supplierRepo.findProjectedByNameContainsIgnoreCase(name, SupplierView.class).forEach(foundSuppliers::add);
		List<SupplierView> result = foundSuppliers.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public SupplierDetailView getSupplierDetailById(long id) {
		SupplierDetailView supplierDetail = supplierRepo.findProjectedById(id, SupplierDetailView.class);
		return supplierDetail;
	}
	
	public SupplierPurchaseView getSupplierPurchaseBySupplierId(long id){
		SupplierPurchaseView supplierPurchaseDetail = supplierRepo.findProjectedById(id, SupplierPurchaseView.class);
		return supplierPurchaseDetail;
	}

	public void createSupplier(Supplier newSupplier) {
		SupplierContact newContact = newSupplier.getContact();
		newContact.setSupplier(newSupplier);
		newSupplier.setContact(newContact);
		contactRepo.save(newContact);
	}
	
	public void updateSupplier (long supplierId, Supplier updatedSupplier) {
		Supplier foundSupplier = getSupplierById(supplierId).get();
		
		foundSupplier.setContactPerson(updatedSupplier.getContactPerson());
		foundSupplier.setEmail(updatedSupplier.getEmail());
		foundSupplier.setName(updatedSupplier.getName());
		foundSupplier.setNotice(updatedSupplier.getNotice());
		foundSupplier.setWebsite(updatedSupplier.getWebsite());
		
		supplierRepo.save(foundSupplier);
	}
	
	public void updateSupplierContact (long supplierId, long contactId, SupplierContact updatedContact) {
		Supplier foundSupplier = getSupplierById(supplierId).get();
		SupplierContact foundContact = contactRepo.findById(contactId).get();
		
		foundContact.setAdditionalAddressLine(updatedContact.getAdditionalAddressLine());
		foundContact.setCity(updatedContact.getCity());
		foundContact.setCountry(updatedContact.getCountry());
		foundContact.setMobileNumber(updatedContact.getMobileNumber());
		foundContact.setPhoneNumber(updatedContact.getPhoneNumber());
		foundContact.setPostcode(updatedContact.getPostcode());
		foundContact.setStreetName(updatedContact.getStreetName());
		foundContact.setStreetNumber(updatedContact.getStreetNumber());
		
		foundContact.setSupplier(foundSupplier);
		foundSupplier.setContact(foundContact);
		contactRepo.save(foundContact);
		
	}

	
	public BigDecimal getTotalListingPrice (Set<PurchaseItem>purchaseItems) {
		purchaseItems.forEach(item ->{
			Product foundProduct = productRepo.findById(item.getProduct().getId()).get();
			BigDecimal itemPrice = foundProduct.getListingPrice();
			int itemQuantity = item.getQuantity();
			BigDecimal itemCost = BigDecimal.ZERO;
			itemCost = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
			totalCost=totalCost.add(itemCost);
		});
		return totalCost;
	}
	
	public void saveNewPurchase (long supplierId, Purchase newPurchase) {
		Supplier foundSupplier = supplierRepo.findById(supplierId).get();
		Set<PurchaseItem>purchaseItems = newPurchase.getPurchaseItem();
		BigDecimal totalCost = getTotalListingPrice (purchaseItems);
		
		newPurchase.setTotal(totalCost);
		newPurchase.setSupplier(foundSupplier);
		
		purchaseItems.forEach(item ->{
			item.setPurchase(newPurchase);
			newPurchase.getPurchaseItem().add(item);
		});
		
		//PurchaseShipping newPurchaseShipping = newPurchase.getPurchaseshipping();
		PurchaseShipping newPurchaseShipping = new PurchaseShipping();
		newPurchaseShipping.setStatus("Processing");
		newPurchaseShipping.setPurchase(newPurchase);
		newPurchase.setPurchaseshipping(newPurchaseShipping);
		purchaseshippingRepo.save(newPurchaseShipping);
		Purchase savedPurchase = purchaseRepo.save(newPurchase);
		
		PurchaseHistory newPurchaseHistory = new PurchaseHistory();
		newPurchaseHistory.setStatus("Purchase made");
		newPurchaseHistory.setPurchase(savedPurchase);
		savedPurchase.getPurchasehistory().add(newPurchaseHistory);
		purchasehistoryRepo.save(newPurchaseHistory);
		
		purchaseItems.forEach(item ->{
			Optional <Product> productData = productRepo.findById(item.getProduct().getId());
			if(productData.isPresent()) {
				Product foundProduct = productData.get();
				foundProduct.getPurchaseItem().add(item);
				item.setProduct(foundProduct);
				productRepo.save(foundProduct);
			}
		});
	}
	
	

	
}
