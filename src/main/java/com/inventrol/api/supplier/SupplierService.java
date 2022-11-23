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

import com.inventrol.api.contact.Contact;
import com.inventrol.api.contact.ContactRepository;
import com.inventrol.api.product.Product;
import com.inventrol.api.product.ProductRepository;
import com.inventrol.api.purchase.Purchase;
import com.inventrol.api.purchase.PurchaseHistory;
import com.inventrol.api.purchase.PurchaseHistoryRepository;
import com.inventrol.api.purchase.PurchaseItem;
import com.inventrol.api.purchase.PurchaseItemRepository;
import com.inventrol.api.purchase.PurchaseRepository;
import com.inventrol.api.purchase.PurchaseShipping;
import com.inventrol.api.purchase.PurchaseShippingRepository;

@Service
public class SupplierService {
	
	private BigDecimal totalCost = BigDecimal.ZERO;

	@Autowired 
	private SupplierRepository supplierRepo;
	
	@Autowired
	private ContactRepository contactRepo;
	
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
	

	public void createSupplier(Supplier newSupplier) {
		Contact newContact = newSupplier.getContact();
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
	
	public void updateSupplierContact (long supplierId, long contactId, Contact updatedContact) {
		Supplier foundSupplier = getSupplierById(supplierId).get();
		Contact foundContact = contactRepo.findById(contactId).get();
		
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
		
		PurchaseShipping newPurchaseShipping = newPurchase.getPurchaseshipping();
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
