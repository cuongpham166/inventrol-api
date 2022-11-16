package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.attributevalue.AttributeValue;
import com.inventrol.api.brand.Brand;
import com.inventrol.api.discount.Discount;
import com.inventrol.api.orderitem.OrderItem;
import com.inventrol.api.purchaseitem.PurchaseItem;
import com.inventrol.api.record.ListingPriceRecord;
import com.inventrol.api.record.RetailPriceRecord;
import com.inventrol.api.subcategory.Subcategory;
import com.inventrol.api.supplier.Supplier;

@Entity
@Table(name = "product")
public class Product {

	public Product(ProductStock productstock, Brand brand, Discount discount, Subcategory subcategory,
			Set<ListingPriceRecord> listingPriceRecord, Set<RetailPriceRecord> retailPriceRecord,
			Set<PurchaseItem> purchaseItem, Set<OrderItem> orderItem, Set<Supplier> supplier,
			Set<AttributeValue> attributeValue, String name, String barcode, String sku, int vat,
			BigDecimal retailPrice, BigDecimal listingPrice, String notice, boolean deleted, LocalDateTime createdOn,
			String createdBy, LocalDateTime updatedOn, String updatedBy) {
		super();
		this.productstock = productstock;
		this.brand = brand;
		this.discount = discount;
		this.subcategory = subcategory;
		this.listingPriceRecord = listingPriceRecord;
		this.retailPriceRecord = retailPriceRecord;
		this.purchaseItem = purchaseItem;
		this.orderItem = orderItem;
		this.supplier = supplier;
		this.attributeValue = attributeValue;
		this.name = name;
		this.barcode = barcode;
		this.sku = sku;
		this.vat = vat;
		this.retailPrice = retailPrice;
		this.listingPrice = listingPrice;
		this.notice = notice;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Product() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="product_stock_id", nullable=false)
	private ProductStock productstock;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="brand_id", nullable=false)
	private Brand brand;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="discount_id", nullable=false)
	private Discount discount;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="subcategory_id", nullable=false)
	private Subcategory subcategory;
	
	@OneToMany(mappedBy ="product",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<ListingPriceRecord>listingPriceRecord = new HashSet<ListingPriceRecord>();
	
	@OneToMany(mappedBy ="product",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<RetailPriceRecord>retailPriceRecord = new HashSet<RetailPriceRecord>();
	
	@OneToMany(mappedBy ="product",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PurchaseItem>purchaseItem = new HashSet<PurchaseItem>();
	
	@OneToMany(mappedBy ="product",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<OrderItem>orderItem = new HashSet<OrderItem>();
	
	@ManyToMany
	@JoinTable(name="product_supplier",
	joinColumns= {@JoinColumn(name = "product_id")},
	inverseJoinColumns= {@JoinColumn(name = "supplier_id")})
	private Set<Supplier> supplier = new HashSet<Supplier>();
	
	@ManyToMany
	@JoinTable(name="product_attribute_value",
	joinColumns= {@JoinColumn(name = "product_id")},
	inverseJoinColumns= {@JoinColumn(name = "attribute_value_id")})
	private Set<AttributeValue>attributeValue = new HashSet<AttributeValue>();
	
	@Column(name = "name")
	private String name;
	
	@Column(name="barcode")
	private String barcode;
	
	@Column(name="sku")
	private String sku;
	

	@Column(name="vat")
	private int vat;
	
	@Column(name="retail_price",precision=10, scale=2)
	private BigDecimal retailPrice;

	@Column(name="listing_price",precision=10, scale=2)
	private BigDecimal listingPrice;
	
	@Column(name = "notice")
	private String notice;
		
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
 
    @Column(name = "created_by")
    private String createdBy;
     
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
 
    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }
 
    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
    
	public long getId() {
		return id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Set<ListingPriceRecord> getListingPriceRecord() {
		return listingPriceRecord;
	}

	public void setListingPriceRecord(Set<ListingPriceRecord> listingPriceRecord) {
		this.listingPriceRecord = listingPriceRecord;
	}

	public Set<RetailPriceRecord> getRetailPriceRecord() {
		return retailPriceRecord;
	}

	public void setRetailPriceRecord(Set<RetailPriceRecord> retailPriceRecord) {
		this.retailPriceRecord = retailPriceRecord;
	}

	public Set<Supplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(Set<Supplier> supplier) {
		this.supplier = supplier;
	}

	public Set<AttributeValue> getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(Set<AttributeValue> attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getListingPrice() {
		return listingPrice;
	}

	public void setListingPrice(BigDecimal listingPrice) {
		this.listingPrice = listingPrice;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}



	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Set<PurchaseItem> getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(Set<PurchaseItem> purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}



	public LocalDateTime getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}



	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public ProductStock getProductstock() {
		return productstock;
	}

	public void setProductstock(ProductStock productstock) {
		this.productstock = productstock;
	}
}
