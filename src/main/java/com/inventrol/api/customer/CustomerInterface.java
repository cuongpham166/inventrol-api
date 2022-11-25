package com.inventrol.api.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.inventrol.api.customer.customeraddress.CustomerAddress;
import com.inventrol.api.customer.customeraddress.CustomerAddressDetailView;
import com.inventrol.api.customer.customeraddress.CustomerAddressView;
import com.inventrol.api.order.Order;
import com.inventrol.api.order.orderitem.OrderItem;

public interface CustomerInterface {
	public Optional<Customer>findCustomerById(long id);
	public Optional<CustomerAddress>findCustomerAddressById(long id);
	public List<CustomerView>getAllCustomer();
	public List<CustomerView>searchCustomer (String name);
	public CustomerDetailView getCustomerDetailById(long id);
	public CustomerAddressView getAddressByCustomerId(long id);
	public void createCustomer (NewCustomerPayload newCustomerPayload);
	public void createCustomerAddress (long customerId, CustomerAddress newCustomerAddress);
	public CustomerAddressDetailView getCustomerAddressDetailById(long id);
	public void updateCustomer (long customerId, Customer updatedCustomer);
	public void setPrimaryAddress (long customerId, long addressId);
	public void updateCustomerAddress (long customerId, long addressId, CustomerAddress updatedCustomerAddress);
	public BigDecimal getSubtotal (Set<OrderItem>orderitems);
	public BigDecimal getTotalDiscount (Set<OrderItem>orderitems);
	public BigDecimal getTotalVat (Set<OrderItem>orderitems);
	public void saveNewOrder (long customerId, Order newOrder);
}
