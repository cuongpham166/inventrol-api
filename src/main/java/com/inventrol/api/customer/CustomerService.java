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
	
}
