package com.inventrol.api.customer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.customeraddress.CustomerAddress;
import com.inventrol.api.order.Order;

@Entity
@Table(name = "customer")
public class Customer {
	public Customer() {
		super();
	}

	public Customer(Set<CustomerAddress> customeradress, Set<Order> order, String name, String email,
			String mobileNumber, String notice, boolean deleted, LocalDate createdDate, LocalDate updatedDate) {
		super();
		this.customeradress = customeradress;
		this.order = order;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.notice = notice;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy ="customer",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<CustomerAddress> customeradress = new HashSet<CustomerAddress>();
	
	@OneToMany(mappedBy ="customer",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Order>order = new HashSet<Order>();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;

	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;

	public Set<CustomerAddress> getCustomeradress() {
		return customeradress;
	}

	public void setCustomeradress(Set<CustomerAddress> customeradress) {
		this.customeradress = customeradress;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public long getId() {
		return id;
	}

}
