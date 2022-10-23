package com.inventrol.api.payment;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.purchase.Purchase;

@Entity
@Table(name = "payment")
public class Payment {
	public Payment() {
		super();
	}

	public Payment(Purchase purchase, String status, String paymentType, String notice, boolean deleted,
			LocalDate createdDate, LocalDate updatedDate) {
		super();
		this.purchase = purchase;
		this.status = status;
		this.paymentType = paymentType;
		this.notice = notice;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="payment")
	private Purchase purchase;
	
	@Column(name = "status")
	private String status = "processing";//"processing, completed, failed, refunded"

	@Column(name="payment_type")
	private String paymentType;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;

	public long getId() {
		return id;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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

}
