package com.inventrol.api.shipping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.order.Order;

@Entity
@Table(name = "shipping")
public class Shipping {
	public Shipping() {
		super();
	}

	public Shipping(Order order, String service, String packageType, String trackingNumber,
			String notice, boolean deleted, LocalDateTime createdOn, LocalDateTime updatedOn) {
		super();
		this.order = order;
		this.service = service;
		this.packageType = packageType;
		this.trackingNumber = trackingNumber;
		this.notice = notice;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="shipping")
	private Order order;
	
	@Column(name = "service")
	private String service;
	
	@Column(name = "package_type")
	private String packageType;
	
	@Column(name = "tracking_number")
	private String trackingNumber;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
    
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }
 
    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public long getId() {
		return id;
	}
	
}
