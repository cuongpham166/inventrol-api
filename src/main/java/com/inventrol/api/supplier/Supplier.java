package com.inventrol.api.supplier;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.contact.Contact;

@Entity
@Table(name = "supplier")
public class Supplier {
	public Supplier(Contact contact, String name, String contactPerson, String notice, boolean deleted,
			LocalDate createdDate, LocalDate updatedDate) {
		super();
		this.contact = contact;
		this.name = name;
		this.contactPerson = contactPerson;
		this.notice = notice;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
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
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "contact_person")
	private String contactPerson;
	
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
