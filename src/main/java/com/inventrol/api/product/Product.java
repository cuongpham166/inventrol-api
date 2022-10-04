package com.inventrol.api.product;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.attributevalue.AttributeValue;
import com.inventrol.api.brand.Brand;
import com.inventrol.api.listingpricerecord.ListingPriceRecord;
import com.inventrol.api.retailpricerecord.RetailPriceRecord;
import com.inventrol.api.subcategory.Subcategory;
import com.inventrol.api.supplier.Supplier;

@Entity
@Table(name = "product")
public class Product {
	public Product(Brand brand, Subcategory subcategory, Set<ListingPriceRecord> listingPriceRecord,
			Set<RetailPriceRecord> retailPriceRecord, Set<Supplier> supplier, Set<AttributeValue> attributeValue,
			String name, int quantity, int soldNumber, int orderedNumber, String barcode, String sku,
			String stockStatus, BigDecimal vat, BigDecimal retailPrice, BigDecimal listingPrice, String notice,
			LocalDate createdDate, LocalDate updatedDate, boolean deleted) {
		super();
		this.brand = brand;
		this.subcategory = subcategory;
		this.listingPriceRecord = listingPriceRecord;
		this.retailPriceRecord = retailPriceRecord;
		this.supplier = supplier;
		this.attributeValue = attributeValue;
		this.name = name;
		this.quantity = quantity;
		this.soldNumber = soldNumber;
		this.orderedNumber = orderedNumber;
		this.barcode = barcode;
		this.sku = sku;
		this.stockStatus = stockStatus;
		this.vat = vat;
		this.retailPrice = retailPrice;
		this.listingPrice = listingPrice;
		this.notice = notice;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.deleted = deleted;
	}

	public Product() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="brand_id", nullable=false)
	private Brand brand;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="subcategory_id", nullable=false)
	private Subcategory subcategory;
	
	@OneToMany(mappedBy ="product",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<ListingPriceRecord>listingPriceRecord = new HashSet<ListingPriceRecord>();
	
	@OneToMany(mappedBy ="product",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<RetailPriceRecord>retailPriceRecord = new HashSet<RetailPriceRecord>();
	
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
	
	@Column(name="quantity")
	private int quantity = 0;
	
	@Column(name="soldNumber")
	private int soldNumber = 0;
	
	@Column(name="orderedNumber")
	private int orderedNumber = 0;
	
	@Column(name="barcode")
	private String barcode;
	
	@Column(name="sku")
	private String sku;
	
	@Transient
	private String stockStatus;
	
	@Column(name="vat",precision=5, scale=3)
	private BigDecimal vat;
	
	@Column(name="retail_price",precision=10, scale=2)
	private BigDecimal retailPrice;

	@Column(name="listing_price",precision=10, scale=2)
	private BigDecimal listingPrice;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(int soldNumber) {
		this.soldNumber = soldNumber;
	}

	public int getOrderedNumber() {
		return orderedNumber;
	}

	public void setOrderedNumber(int orderedNumber) {
		this.orderedNumber = orderedNumber;
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

	//In Stock, Low in Stock, Out of Stock
	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
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

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
