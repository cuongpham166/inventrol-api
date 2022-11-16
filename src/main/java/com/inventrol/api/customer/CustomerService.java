package com.inventrol.api.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerAddressRepository customerAddressRepo;

	public Optional<Customer>findCustomerById(long id){
		Optional<Customer>foundCustomer = customerRepo.findById(id);
		return foundCustomer;
	}
	
	public Optional<CustomerAddress>findCustomerAddressById(long id){
		Optional<CustomerAddress>foundCustomerAddress = customerAddressRepo.findById(id);
		return foundCustomerAddress;
	}
	
	public List<CustomerView>getAllCustomer(){
		List<CustomerView> customers = new ArrayList<CustomerView>();
		customerRepo.findAllProjectedByOrderByIdAsc(CustomerView.class).forEach(customers::add);
		List<CustomerView> result = customers.stream().filter(cus -> cus.isDeleted()==false).collect(Collectors.toList());
		return result;
	}
	
	public List<CustomerView>searchCustomer (String name){
		List<CustomerView>foundCustomers = new ArrayList<CustomerView>();
		customerRepo.findProjectedByNameContainsIgnoreCase(name, CustomerView.class).forEach(foundCustomers::add);
		List<CustomerView> result = foundCustomers.stream().filter(cus -> cus.isDeleted() == false).collect(Collectors.toList());
		return result;
	}
	
	public CustomerDetailView getCustomerDetailById(long id) {
		CustomerDetailView customerDetail = customerRepo.findProjectedById(id, CustomerDetailView.class);
		return customerDetail;
	}
	
	public CustomerAddressView getAddressByCustomerId(long id) {
		CustomerAddressView customerAddresses = customerRepo.findProjectedById(id, CustomerAddressView.class);
		return customerAddresses;
	}
	
	public void createCustomer (NewCustomerPayload newCustomerPayload) {
		Customer customer = newCustomerPayload.getCustomer();
		CustomerAddress customerAddress = newCustomerPayload.getCustomerAddress();
		
		Customer savedCustomer = customerRepo.save(customer);
		
		customerAddress.setPrimary(true);
		
		customerAddress.setCustomer(savedCustomer);
		savedCustomer.getCustomeradress().add(customerAddress);
		customerAddressRepo.save(customerAddress);
	}
	
	public void createCustomerAddress (long customerId, CustomerAddress newCustomerAddress) {
		Customer foundCustomer = findCustomerById(customerId).get();
		foundCustomer.getCustomeradress().add(newCustomerAddress);
		newCustomerAddress.setCustomer(foundCustomer);
		customerAddressRepo.save(newCustomerAddress);		
	}
	
	public CustomerAddressDetailView getCustomerAddressDetailById(long id) {
		CustomerAddressDetailView customerAddressDetail = customerAddressRepo.findProjectedById(id, CustomerAddressDetailView.class);
		return customerAddressDetail;
	}
	

	public void updateCustomer (long customerId, Customer updatedCustomer) {
		Customer foundCustomer = findCustomerById(customerId).get();
		foundCustomer.setEmail(updatedCustomer.getEmail());
		foundCustomer.setMobileNumber(updatedCustomer.getMobileNumber());
		foundCustomer.setName(updatedCustomer.getName());
		foundCustomer.setNotice(updatedCustomer.getNotice());
		customerRepo.save(foundCustomer);
	}
	
	public void updateCustomerAddress (long customerId, long addressId, CustomerAddress updatedCustomerAddress) {
		Customer foundCustomer = findCustomerById(customerId).get();
		CustomerAddress _customerAddress = findCustomerAddressById(addressId).get();
		
		_customerAddress.setAdditionalAddressLine(updatedCustomerAddress.getAdditionalAddressLine());
		_customerAddress.setCity(updatedCustomerAddress.getCity());
		_customerAddress.setCountry(updatedCustomerAddress.getCountry());
		_customerAddress.setNotice(updatedCustomerAddress.getNotice());
		_customerAddress.setPostcode(updatedCustomerAddress.getPostcode());
		_customerAddress.setStreetName(updatedCustomerAddress.getStreetName());
		_customerAddress.setStreetNumber(updatedCustomerAddress.getStreetNumber());
		_customerAddress.setCustomer(foundCustomer);
		
		customerAddressRepo.save(_customerAddress);
	}
}
