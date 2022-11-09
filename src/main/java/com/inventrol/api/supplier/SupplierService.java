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
import com.inventrol.api.purchase.PurchaseRepository;
import com.inventrol.api.purchase.PurchaseShipping;
import com.inventrol.api.purchaseitem.PurchaseItem;

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
	
	public BigDecimal getTotalListingPrice (Set<PurchaseItem>purchaseItems) {
		BigDecimal totalCost = BigDecimal.ZERO;
		/*BigDecimal totalCost = BigDecimal.ZERO;
		BigDecimal itemCost = BigDecimal.ZERO;
		
		purchaseItems.forEach(item ->{
			Optional <Product> productData = productRepo.findById(item.getProduct().getId());
			Product foundProduct = productData.get();
			BigDecimal itemPrice = foundProduct.getListingPrice();
			int itemQuantity = item.getQuantity();
			itemCost = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
			totalCost = totalCost.add(itemCost);
		});*/
		return totalCost;
	}
	
	public BigDecimal getTotalListingPriceTest (Set<PurchaseItem>purchaseItems) {
		purchaseItems.forEach(item ->{
			long productId = productRepo.findOneByName(item.getProduct().getName()).getId();
			Optional <Product> productData = productRepo.findById(productId);
			Product foundProduct = productData.get();
			BigDecimal itemPrice = foundProduct.getListingPrice();
			int itemQuantity = item.getQuantity();
			BigDecimal itemCost = BigDecimal.ZERO;
			itemCost = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
			totalCost=totalCost.add(itemCost);
		});
		return totalCost;
	}
	

	
	public BigDecimal createPurchase(long supplierId, Purchase newPurchase) {
		Set<PurchaseItem>purchaseItems = newPurchase.getPurchaseItem();
		BigDecimal totalCost = getTotalListingPrice (purchaseItems);
		
	
		Optional<Supplier>supplierData = supplierRepo.findById(supplierId);
		Supplier foundSupplier = supplierData.get();
		
		purchaseItems.forEach(item ->{
			item.setPurchase(newPurchase);
			newPurchase.getPurchaseItem().add(item);
		});
		
		newPurchase.setTotal(totalCost);
		newPurchase.setSupplier(foundSupplier);
		purchaseRepo.save(newPurchase);
		
		purchaseItems.forEach(item ->{
			Optional <Product> productData = productRepo.findById(item.getProduct().getId());
			Product foundProduct = productData.get();
			foundProduct.getPurchaseItem().add(item);
			productRepo.save(foundProduct);
		});
		
		return totalCost;
	}
	
	public BigDecimal createPurchaseTest(long supplierId, Purchase newPurchase) {
		Set<PurchaseItem>purchaseItems = newPurchase.getPurchaseItem();
		BigDecimal totalCost = getTotalListingPriceTest (purchaseItems);
		
		Optional<Supplier>supplierData = supplierRepo.findById(supplierId);
		Supplier foundSupplier = supplierData.get();
		
		purchaseItems.forEach(item ->{
			item.setPurchase(newPurchase);
			newPurchase.getPurchaseItem().add(item);
		});
		
		PurchaseShipping newPurchaseshipping = new PurchaseShipping();
		newPurchase.setPurchaseshipping(newPurchaseshipping);
		
		newPurchase.setTotal(totalCost);
		newPurchase.setSupplier(foundSupplier);
		purchaseRepo.save(newPurchase);
		
		purchaseItems.forEach(item ->{
			long productId = productRepo.findOneByName(item.getProduct().getName()).getId();
			Optional <Product> productData = productRepo.findById(productId);
			Product foundProduct = productData.get();
			foundProduct.getPurchaseItem().add(item);
			productRepo.save(foundProduct);
		});
		//purchaseRepo.save(newPurchase); //save purchase ở đây không được. phải save purchase trước rồi mới save product sau
		return totalCost;
	}
	
}
