package com.inventrol.api.listingpricerecord;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.brand.Brand;
import com.inventrol.api.product.Product;

@Entity
@Table(name = "listing_price_record")
public class ListingPriceRecord {
	public ListingPriceRecord(Product product, BigDecimal price, LocalDate createdDate, boolean deleted) {
		super();
		this.product = product;
		this.price = price;
		this.createdDate = createdDate;
		this.deleted = deleted;
	}

	public ListingPriceRecord() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@Column(name="price",precision=10, scale=2)
	private BigDecimal price;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

	public long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
