package com.inventrol.api.purchase;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "purchase_shipping")
public class PurchaseShipping {
	public PurchaseShipping() {
		super();
	}

	public PurchaseShipping(Purchase purchase, String service, String trackingNumber, String status, String notice,
			boolean deleted, LocalDateTime updatedOn, String updatedBy) {
		super();
		this.purchase = purchase;
		this.service = service;
		this.trackingNumber = trackingNumber;
		this.status = status;
		this.notice = notice;
		this.deleted = deleted;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="purchaseshipping")
	private Purchase purchase;
	
	@Column(name = "service")
	private String service;
	
	@Column(name = "tracking_number")
	private String trackingNumber;
	
	@Column(name = "status")
	private String status="Processing"; // "processing, confirmed, shipped, checking, completed, returned"
	
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

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
