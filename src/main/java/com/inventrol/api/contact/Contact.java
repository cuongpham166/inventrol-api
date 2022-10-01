package com.inventrol.api.contact;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.supplier.Supplier;

@Entity
@Table(name = "contact")
public class Contact {
	public Contact(Supplier supplier, String email, String website, String phoneNumber, String mobileNumber,
			String streetName, String streetNumber, String additionalAddressLine, String postcode, String city,
			String country, boolean deleted) {
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

}
