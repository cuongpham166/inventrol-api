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
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.payment.Payment;
import com.inventrol.api.purchaseitem.PurchaseItem;
import com.inventrol.api.supplier.Supplier;

@Entity
@Table(name = "purchase")
public class Purchase {
	public Purchase(Set<PurchaseItem> purchaseItem, Supplier supplier, String status, BigDecimal total,
			String paymentType, String courier, String trackingNumber, String notice, boolean deleted,
			LocalDateTime createdOn, String createdBy, LocalDateTime updatedOn, String updatedBy) {
		super();
		this.purchaseItem = purchaseItem;
		this.supplier = supplier;
		this.status = status;
		this.total = total;
		this.paymentType = paymentType;
		this.courier = courier;
		this.trackingNumber = trackingNumber;
		this.notice = notice;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Purchase() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy ="purchase",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PurchaseItem>purchaseItem = new HashSet<PurchaseItem>();
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="supplier_id", nullable=false)
	private Supplier supplier;
	
	@Column(name = "status")
	private String status="in process"; // "in process, completed, returned"
	
	@Column(name="total",precision=10, scale=2)
	private BigDecimal total;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name = "courier")
	private String courier;
	
	@Column(name = "tracking_number")
	private String trackingNumber;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	
}
