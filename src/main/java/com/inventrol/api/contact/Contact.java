package com.inventrol.api.contact;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.supplier.Supplier;

@Entity
@Table(name = "contact")
public class Contact {


	public Contact(Supplier supplier, String email, String website, String phoneNumber, String mobileNumber,
			String streetName, String streetNumber, String additionalAddressLine, String postcode, String city,
			String country, boolean deleted, LocalDateTime createdOn, String createdBy, LocalDateTime updatedOn,
			String updatedBy) {
		super();
		this.supplier = supplier;
		this.email = email;
		this.website = website;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.additionalAddressLine = additionalAddressLine;
		this.postcode = postcode;
		this.city = city;
		this.country = country;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	public Contact() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="contact")
	private Supplier supplier;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "street_number")
	private String streetNumber;
	
	@Column(name="additional_address_line")
	private String additionalAddressLine;
	
	@Column(name="postcode")
	private String postcode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getAdditionalAddressLine() {
		return additionalAddressLine;
	}

	public void setAdditionalAddressLine(String additionalAddressLine) {
		this.additionalAddressLine = additionalAddressLine;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
