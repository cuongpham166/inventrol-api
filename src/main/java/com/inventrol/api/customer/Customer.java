package com.inventrol.api.customer;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.order.Order;

@Entity
@Table(name = "customer")
public class Customer {
	public Customer(Set<CustomerAddress> customeradress, Set<Order> order, String name, String email,
			String mobileNumber, String notice, boolean deleted, LocalDateTime createdOn, String createdBy,
			LocalDateTime updatedOn, String updatedBy) {
		super();
		this.customeradress = customeradress;
		this.order = order;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.notice = notice;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Customer() {
		super();
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



	public long getId() {
		return id;
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

}
