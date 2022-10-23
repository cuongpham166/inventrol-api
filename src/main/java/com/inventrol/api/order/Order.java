package com.inventrol.api.order;

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

import com.inventrol.api.customer.Customer;
import com.inventrol.api.orderitem.OrderItem;
import com.inventrol.api.payment.Payment;

@Entity
@Table(name = "orders")
public class Order {
	public Order() {
		super();
	}

	public Order(Payment payment, Set<OrderItem> orderitem, Customer customer, String status, BigDecimal total,
			String notice, LocalDate orderDate, LocalDateTime orderTime, LocalDate updatedDate, boolean deleted) {
		super();
		this.payment = payment;
		this.orderitem = orderitem;
		this.customer = customer;
		this.status = status;
		this.total = total;
		this.notice = notice;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.updatedDate = updatedDate;
		this.deleted = deleted;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="payment_id", nullable=false)
	private Payment payment;
	
	@OneToMany(mappedBy ="order",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<OrderItem>orderitem = new HashSet<OrderItem>();
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	@Column(name = "status")
	private String status="processing"; // "processing, ready to ship, shipped, completed, returned"
	
	@Column(name="total",precision=10, scale=2)
	private BigDecimal total;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name="order_date")
	private LocalDate orderDate;
	
	@Column (name="order_time")
	private LocalDateTime orderTime;
	
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

	public Set<OrderItem> getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(Set<OrderItem> orderitem) {
		this.orderitem = orderitem;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
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
