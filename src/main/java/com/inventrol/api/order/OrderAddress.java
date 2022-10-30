package com.inventrol.api.order;

import javax.persistence.Embeddable;

@Embeddable
public class OrderAddress {
	public OrderAddress() {
		super();
	}
	public OrderAddress(String streetName, String streetNumber, String additionalAddressLine, String postcode,
			String city, String country, String notice) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.additionalAddressLine = additionalAddressLine;
		this.postcode = postcode;
		this.city = city;
		this.country = country;
		this.notice = notice;
	}
	
	private String streetName;
	private String streetNumber;
	private String additionalAddressLine;
	private String postcode;
	private String city;
	private String country;
	private String notice;
	
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
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
}
