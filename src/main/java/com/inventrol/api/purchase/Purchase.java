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
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.payment.Payment;
import com.inventrol.api.purchaseitem.PurchaseItem;
import com.inventrol.api.supplier.Supplier;

@Entity
@Table(name = "purchase")
public class Purchase {
	public Purchase() {
		super();
	}

	public Purchase(Payment payment, Set<PurchaseItem> purchaseItem, Supplier supplier, String status, BigDecimal total,
			String notice, LocalDate purchaseDate, LocalDateTime purchaseTime, LocalDate updatedDate, boolean deleted) {
		super();
		this.payment = payment;
		this.purchaseItem = purchaseItem;
		this.supplier = supplier;
		this.status = status;
		this.total = total;
		this.notice = notice;
		this.purchaseDate = purchaseDate;
		this.purchaseTime = purchaseTime;
		this.updatedDate = updatedDate;
		this.deleted = deleted;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="payment_id", nullable=false)
	private Payment payment;
	
	@OneToMany(mappedBy ="purchase",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PurchaseItem>purchaseItem = new HashSet<PurchaseItem>();
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="supplier_id", nullable=false)
	private Supplier supplier;
	
	@Column(name = "status")
	private String status="processing"; // "processing, completed, returned"
	
	@Column(name="total",precision=10, scale=2)
	private BigDecimal total;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name="purchase_date")
	private LocalDate purchaseDate;
	
	@Column (name="purchase_time")
	private LocalDateTime purchaseTime;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

	public long getId() {
		return id;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDateTime getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(LocalDateTime purchaseTime) {
		this.purchaseTime = purchaseTime;
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
