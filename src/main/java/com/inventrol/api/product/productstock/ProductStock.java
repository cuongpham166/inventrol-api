package com.inventrol.api.product.productstock;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.product.Product;

@Entity
@Table(name = "product_stock")
public class ProductStock {
	public ProductStock() {
		super();
	}

	public ProductStock(Product product, int quantity, int soldNumber, int orderedNumber, String stockStatus,
			String notice, boolean deleted, LocalDateTime updatedOn, String updatedBy) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.soldNumber = soldNumber;
		this.orderedNumber = orderedNumber;
		this.stockStatus = stockStatus;
		this.notice = notice;
		this.deleted = deleted;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="productstock")
	private Product product;
	
	@Column(name="quantity")
	private int quantity = 0;
	
	@Column(name="soldNumber")
	private int soldNumber = 0;
	
	@Column(name="orderedNumber")
	private int orderedNumber = 0;
	
	@Transient
	private String stockStatus;
	
	@Column(name = "notice")
	private String notice;
		
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;
	
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
 
    @Column(name = "updated_by")
    private String updatedBy;
    
    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
    
    @PostLoad
    private void postLoadFunction(){
       if(quantity == 0) {
    	   this.stockStatus = "Out of Stock";
       }else if (quantity > 0 && quantity < 10) {
    	   this.stockStatus = "Low in Stock";
       }else {
    	   this.stockStatus = "In Stock";
       }
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
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

	public long getId() {
		return id;
	}

    
}
