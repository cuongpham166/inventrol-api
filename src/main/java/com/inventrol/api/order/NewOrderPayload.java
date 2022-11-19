package com.inventrol.api.order;

import com.inventrol.api.customer.Customer;

public class NewOrderPayload {
	private Order order;
	private Customer customer;
	private OrderPayment orderPayment;
	private OrderHistory orderHistory;
	private OrderShipping orderShipping;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public OrderPayment getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(OrderPayment orderPayment) {
		this.orderPayment = orderPayment;
	}
	public OrderHistory getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(OrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}
	public OrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	
}
