package com.inventrol.api.product;

import java.math.BigDecimal;
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
import com.inventrol.api.attributevalue.AttributeValueRepository;
import com.inventrol.api.attributevalue.AttributeValueService;
import com.inventrol.api.brand.Brand;
import com.inventrol.api.brand.BrandRepository;
import com.inventrol.api.brand.BrandService;
import com.inventrol.api.discount.Discount;
import com.inventrol.api.discount.DiscountRepository;
import com.inventrol.api.discount.DiscountService;
import com.inventrol.api.product.productstock.ProductStock;
import com.inventrol.api.product.productstock.ProductStockRepository;
import com.inventrol.api.record.listingpricerecord.ListingPriceRecord;
import com.inventrol.api.record.listingpricerecord.ListingPriceRecordRepository;
import com.inventrol.api.record.retailpricerecord.RetailPriceRecord;
import com.inventrol.api.record.retailpricerecord.RetailPriceRecordRepository;
import com.inventrol.api.subcategory.Subcategory;
import com.inventrol.api.subcategory.SubcategoryRepository;
import com.inventrol.api.subcategory.SubcategoryService;
import com.inventrol.api.supplier.Supplier;
import com.inventrol.api.supplier.SupplierRepository;
import com.inventrol.api.supplier.SupplierService;

@Service
public class ProductService implements ProductInterface {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductStockRepository productstockRepo;
	
	@Autowired
	private ListingPriceRecordRepository listingPriceRecordRepo;
	
	@Autowired
	private RetailPriceRecordRepository retailPriceRecordRepo;
	
	@Autowired 
	private SubcategoryRepository subcategoryRepo;
	
	@Autowired 
	private SubcategoryService subcategoryService;
	
	@Autowired
	private BrandRepository brandRepo;
	
	@Autowired
	private DiscountRepository discountRepo;
	
	@Autowired
	private AttributeValueRepository attributeValueRepo;
	
	@Autowired
	private SupplierRepository supplierRepo;
	
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
	
	
	public String createSKU (Product newProduct) {
		Subcategory foundSubcategory = subcategoryService.getSubcategoryById(newProduct.getSubcategory().getId()).get();
		Set<AttributeValue>attributeValue = newProduct.getAttributeValue();
		AttributeValue firstAttributeValue = attributeValue.stream().findFirst().get();
		
		List<ProductView>products = getAllProducts ();
		
		long categoryId = foundSubcategory.getCategory().getId();
		long subcategoryId = newProduct.getSubcategory().getId();
		long brandId = newProduct.getBrand().getId();
		long attributeValueId = firstAttributeValue.getId();
		long sequential = products.size()+1; 
		
		String categoryValue =  String.format("%03d", categoryId);
		String subcategoryValue = String.format("%03d", subcategoryId);
		String brandValue = String.format("%03d", brandId);
		String attributeValueValue = String.format("%03d", attributeValueId);
		
		String skuString = "CP-"+categoryValue+"-"+subcategoryValue+"-"+brandValue+"-"+attributeValueValue+"-"+sequential;
		
		return skuString;
	}
	
	public void saveNewProduct (Product newProduct){
		newProduct.setSku(createSKU(newProduct));
		
		Optional<Subcategory>subcategoryData = subcategoryRepo.findById(newProduct.getSubcategory().getId());
		Optional<Brand>brandData= brandRepo.findById(newProduct.getBrand().getId());
		Optional<Discount>discountData = discountRepo.findById(newProduct.getDiscount().getId());
		
		//One to One
		ProductStock newProductStock = new ProductStock();
		newProductStock.setProduct(newProduct);
		newProduct.setProductstock(newProductStock);
		productstockRepo.save(newProductStock);
		//One to One
		
		//One to Many
		RetailPriceRecord newRetailPrice = new RetailPriceRecord();
		newRetailPrice.setPrice(newProduct.getRetailPrice());
		newRetailPrice.setProduct(newProduct);
		newProduct.getRetailPriceRecord().add(newRetailPrice);

		ListingPriceRecord newListingPrice = new ListingPriceRecord();
		newListingPrice.setPrice(newProduct.getListingPrice());
		newListingPrice.setProduct(newProduct);
		newProduct.getListingPriceRecord().add(newListingPrice);
		//One to Many
		productRepo.save(newProduct);
		
		if(subcategoryData.isPresent() && brandData.isPresent() && discountData.isPresent()) {
			//Many to One
			newProduct.setSubcategory(subcategoryData.get());
			subcategoryData.get().getProduct().add(newProduct);
			subcategoryRepo.save(subcategoryData.get());
			
			newProduct.setBrand(brandData.get());
			brandData.get().getProduct().add(newProduct);
			brandRepo.save(brandData.get());
			
			newProduct.setDiscount(discountData.get());
			discountData.get().getProduct().add(newProduct);
			discountRepo.save(discountData.get());
			//Many to One
			
			//Many to Many
			Set<AttributeValue>attributeValueList = newProduct.getAttributeValue();
			attributeValueList.forEach(attVal ->{
				Optional<AttributeValue>attributeValueData = attributeValueRepo.findById(attVal.getId());
				if(attributeValueData.isPresent()) {
					AttributeValue _attributeValue = attributeValueData.get();
					newProduct.getAttributeValue().add(_attributeValue);
					_attributeValue.getProduct().add(newProduct);
					attributeValueRepo.save(_attributeValue);
				}
			});
			
			Set<Supplier>supplierList = newProduct.getSupplier();
			supplierList.forEach(supp ->{
				Optional<Supplier>supplierData = supplierRepo.findById(supp.getId());
				if(supplierData.isPresent()) {
					Supplier _supplier = supplierData.get();
					newProduct.getSupplier().add(_supplier);
					_supplier.getProduct().add(newProduct);
					supplierRepo.save(_supplier);
				}
			});
			//Many to Many
		}
	}
	

}
