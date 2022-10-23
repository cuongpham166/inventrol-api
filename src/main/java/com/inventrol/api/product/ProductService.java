package com.inventrol.api.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.atrribute.AttributeView;
import com.inventrol.api.attributevalue.AttributeValue;
import com.inventrol.api.attributevalue.AttributeValueService;
import com.inventrol.api.brand.Brand;
import com.inventrol.api.brand.BrandService;
import com.inventrol.api.discount.Discount;
import com.inventrol.api.discount.DiscountService;
import com.inventrol.api.listingpricerecord.ListingPriceRecord;
import com.inventrol.api.listingpricerecord.ListingPriceRecordRepository;
import com.inventrol.api.retailpricerecord.RetailPriceRecord;
import com.inventrol.api.retailpricerecord.RetailPriceRecordRepository;
import com.inventrol.api.subcategory.Subcategory;
import com.inventrol.api.subcategory.SubcategoryService;
import com.inventrol.api.supplier.Supplier;
import com.inventrol.api.supplier.SupplierService;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private AttributeValueService attributeValueService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private ListingPriceRecordRepository listingPriceRecordRepo;
	
	@Autowired
	private RetailPriceRecordRepository retailPriceRecordRepo;
	
	public Optional<Product> getProductById(long id){
		Optional<Product>foundProduct = productRepo.findById(id);
		return foundProduct;
	}
	
	public List<ProductView> getAllProducts (){
		List<ProductView>products = new ArrayList<ProductView>();
		productRepo.findAllProjectedByOrderByIdAsc(ProductView.class).forEach(products::add);
		List<ProductView> results = products.stream().filter(res -> res.isDeleted() == false).collect(Collectors.toList());
		return results;
	}
	
	public List<ProductView>searchProduct (String name){
		List<ProductView>foundProducts = new ArrayList<ProductView>();
		productRepo.findProjectedByNameContainsIgnoreCase(name, ProductView.class).forEach(foundProducts::add);
		List<ProductView> result = foundProducts.stream().filter(cat -> cat.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public ProductDetailView getProductDetailById(long id) {
		ProductDetailView productDetail = productRepo.findProjectedById(id, ProductDetailView.class);
		return productDetail;
	}
	
	public void createProduct (Product newProduct) {
		LocalDate createdDate = LocalDate.now(); 
		
		long subcategoryId = newProduct.getSubcategory().getId();
		long brandId = newProduct.getBrand().getId();
		long discountId = newProduct.getDiscount().getId();
		
		Optional<Subcategory>subcategoryData = subcategoryService.getSubcategoryById(subcategoryId);
		Optional<Brand>brandData = brandService.getBrandById(brandId);
		Optional<Discount>discountData = discountService.getDiscountById(discountId);
		productRepo.save(newProduct);
		
		//One to Many
		ListingPriceRecord newListingPrice = new ListingPriceRecord();
		newListingPrice.setCreatedDate(createdDate);
		newListingPrice.setPrice(newProduct.getListingPrice());
		newListingPrice.setProduct(newProduct);
		listingPriceRecordRepo.save(newListingPrice);
		
		RetailPriceRecord newRetailPrice = new RetailPriceRecord();
		newRetailPrice.setCreatedDate(createdDate);
		newRetailPrice.setPrice(newProduct.getRetailPrice());
		newRetailPrice.setProduct(newProduct);
		retailPriceRecordRepo.save(newRetailPrice);
		//One to Many
				
		if(subcategoryData.isPresent() && brandData.isPresent() && discountData.isPresent()) {
			newProduct.setCreatedDate(createdDate);
			//Many to One
			newProduct.setSubcategory(subcategoryData.get());
			newProduct.setBrand(brandData.get());
			newProduct.setDiscount(discountData.get());
			//Many to One
			
			
			//Many to Many
			Set<AttributeValue>attributeValueList = newProduct.getAttributeValue();
			attributeValueList.forEach(attVal ->{
				Optional<AttributeValue>attributeValueData = attributeValueService.getAttributeValueById(attVal.getId());
				if(attributeValueData.isPresent()) {
					AttributeValue _attributeValue = attributeValueData.get();
					newProduct.getAttributeValue().add(_attributeValue);
					//_attributeValue.getProduct().add(newProduct);
				}
			});
			
			Set<Supplier>supplierList = newProduct.getSupplier();
			supplierList.forEach(supp ->{
				Optional<Supplier>supplierData = supplierService.getSupplierById(supp.getId());
				if(supplierData.isPresent()) {
					Supplier _supplier = supplierData.get();
					newProduct.getSupplier().add(_supplier);
					//_supplier.getProduct().add(newProduct);
				}
			});
			//Many to Many
			
			productRepo.save(newProduct);
		}
		
	}
}
