package com.inventrol.api.order.orderhistory;

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

import com.inventrol.api.order.Order;

@Entity
@Table(name = "order_history")
public class OrderHistory {
	public OrderHistory() {
		super();
	}

	public OrderHistory(Order order, String status, boolean deleted, LocalDateTime createdOn) {
		super();
		this.order = order;
		this.status = status;
		this.deleted = deleted;
		this.createdOn = createdOn;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="order_id", nullable=false)
	private Order order;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
    
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
