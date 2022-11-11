package com.inventrol.api.purchase;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {
	public PurchaseHistory() {
		super();
	}

	public PurchaseHistory(Purchase purchase, String status, boolean deleted, LocalDateTime createdOn) {
		super();
		this.purchase = purchase;
		this.status = status;
		this.deleted = deleted;
		this.createdOn = createdOn;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="purchase_id", nullable=false)
	private Purchase purchase;
	
	@Column(name = "status")
	private String status = "Purchase made";
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
    
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public long getId() {
		return id;
	}
}
