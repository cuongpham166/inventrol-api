package com.inventrol.api.supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.contact.Contact;
import com.inventrol.api.product.Product;
import com.inventrol.api.purchase.Purchase;

@Entity
@Table(name = "supplier")
public class Supplier {

	public Supplier(Contact contact, Set<Product> product, Set<Purchase> purchase, String name, String contactPerson,
			String notice, boolean deleted, LocalDateTime createdOn, String createdBy, LocalDateTime updatedOn,
			String updatedBy) {
		super();
		this.contact = contact;
		this.product = product;
		this.purchase = purchase;
		this.name = name;
		this.contactPerson = contactPerson;
		this.notice = notice;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Supplier() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="contact_id", nullable=false)
	private Contact contact;
	
	@ManyToMany(mappedBy="supplier")
	private Set<Product>product = new HashSet<Product>();
	
	@OneToMany(mappedBy ="supplier",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Purchase>purchase = new HashSet<Purchase>();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "contact_person")
	private String contactPerson;
	
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
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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



	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Set<Purchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(Set<Purchase> purchase) {
		this.purchase = purchase;
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
