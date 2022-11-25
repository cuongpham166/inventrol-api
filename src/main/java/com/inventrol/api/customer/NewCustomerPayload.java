package com.inventrol.api.customer;

import com.inventrol.api.customer.customeraddress.CustomerAddress;

public class NewCustomerPayload {
	private Customer customer;
	private CustomerAddress customerAddress;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}
}
