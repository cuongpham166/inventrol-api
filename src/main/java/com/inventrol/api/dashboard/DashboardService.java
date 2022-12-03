package com.inventrol.api.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.atrribute.AttributeRepository;
import com.inventrol.api.brand.BrandRepository;
import com.inventrol.api.category.CategoryRepository;
import com.inventrol.api.customer.CustomerRepository;
import com.inventrol.api.product.ProductRepository;
import com.inventrol.api.product.ProductService;
import com.inventrol.api.purchase.PurchaseService;
import com.inventrol.api.subcategory.SubcategoryRepository;
import com.inventrol.api.supplier.SupplierRepository;

@Service
public class DashboardService implements DashboardInterface{
	
	@Autowired
	private SubcategoryRepository subcategoryRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired 
	private SupplierRepository supplierRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private BrandRepository brandRepo;
	
	@Autowired
	private AttributeRepository attributeRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	public Dashboard getAllDashboardData () {
		Dashboard dashboardData = new Dashboard();
		
		dashboardData.setTotalAttributes(attributeRepo.count());
		dashboardData.setTotalBrands(brandRepo.count());
		dashboardData.setTotalCustomers(customerRepo.count());
		dashboardData.setTotalProducts(productRepo.count());
		dashboardData.setTotalCategories(categoryRepo.count());
		dashboardData.setTotalSubcategories(subcategoryRepo.count());
		dashboardData.setTotalSuppliers(supplierRepo.count());
		
		dashboardData.setStock(productService.getAllProductStock());
		dashboardData.setPurchaseShippingStatus(purchaseService.getAllPurchaseShippingStatus());
		
		return dashboardData;
	}
}
