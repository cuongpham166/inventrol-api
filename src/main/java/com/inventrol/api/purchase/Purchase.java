package com.inventrol.api.purchase;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.purchase.purchasehistory.PurchaseHistory;
import com.inventrol.api.purchase.purchaseitem.PurchaseItem;
import com.inventrol.api.purchase.purchaseshipping.PurchaseShipping;
import com.inventrol.api.supplier.Supplier;

@Entity
@Table(name = "purchase")
public class Purchase {
	public Purchase(PurchaseShipping purchaseshipping, Set<PurchaseItem> purchaseItem,
			Set<PurchaseHistory> purchasehistory, Supplier supplier, BigDecimal total, String paymentType,
			String notice, boolean deleted, LocalDateTime createdOn, String createdBy) {
		super();
		this.purchaseshipping = purchaseshipping;
		this.purchaseItem = purchaseItem;
		this.purchasehistory = purchasehistory;
		this.supplier = supplier;
		this.total = total;
		this.paymentType = paymentType;
		this.notice = notice;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}


	public Purchase() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="purchase_shipping_id", nullable=false)
	private PurchaseShipping purchaseshipping;
	
	@OneToMany(mappedBy ="purchase",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PurchaseItem>purchaseItem = new HashSet<PurchaseItem>();
	
	@OneToMany(mappedBy ="purchase",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PurchaseHistory>purchasehistory = new HashSet<PurchaseHistory>();
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="supplier_id", nullable=false)
	private Supplier supplier;
		
	@Column(name="total",precision=10, scale=2)
	private BigDecimal total;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name = "notice")
	private String notice;
		
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
 
    @Column(name = "created_by")
    private String createdBy;
     

    @Transient
    public int getNumberOfItems() {
        return this.purchaseItem.size();
    }
    
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    
	public long getId() {
		return id;
	}


	public Set<PurchaseItem> getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(Set<PurchaseItem> purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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

	public PurchaseShipping getPurchaseshipping() {
		return purchaseshipping;
	}

	public void setPurchaseshipping(PurchaseShipping purchaseshipping) {
		this.purchaseshipping = purchaseshipping;
	}

	public Set<PurchaseHistory> getPurchasehistory() {
		return purchasehistory;
	}

	public void setPurchasehistory(Set<PurchaseHistory> purchasehistory) {
		this.purchasehistory = purchasehistory;
	}
}
