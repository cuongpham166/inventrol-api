package com.inventrol.api.supplier;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.inventrol.api.purchase.Purchase;
import com.inventrol.api.purchase.purchaseitem.PurchaseItem;
import com.inventrol.api.supplier.suppliercontact.SupplierContact;

public interface SupplierInterface {
	public Optional<Supplier> getSupplierById(long id);
	public List<SupplierView>getAllSuppliers();
	public List<SupplierView>searchSupplier (String name);
	public SupplierDetailView getSupplierDetailById(long id);
	public void createSupplier(Supplier newSupplier);
	public void updateSupplier (long supplierId, Supplier updatedSupplier);
	public void updateSupplierContact (long supplierId, long contactId, SupplierContact updatedContact);
	public BigDecimal getTotalListingPrice (Set<PurchaseItem>purchaseItems);
	public void saveNewPurchase (long supplierId, Purchase newPurchase);
}
