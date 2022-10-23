package com.inventrol.api.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.customeraddress.CustomerAddress;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerView>> getAllCustomers(@RequestParam Optional<String> name) {
		try {
			List<CustomerView> customers = new ArrayList<CustomerView>();
			if(name.isPresent()) {
				customers = customerService.searchCustomer(name.get());
			}else {
				customers = customerService.getAllCustomer();
			}
			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(customers, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDetailView>getCustomerById(@PathVariable("id") long id){
		Optional<Customer> customerData = customerService.findCustomerById(id);
		if(customerData.isPresent()) {
			Customer _customer = customerData.get();
			if(_customer.isDeleted() == false) {
				CustomerDetailView _customerDetail = customerService.getCustomerDetailById(id);
				return new ResponseEntity<>(_customerDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer){
		try{
			customerService.createCustomer(newCustomer);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/customer/{customerId}/address")
	public ResponseEntity<Customer>createAddress(@PathVariable("customerId") long customerId,@RequestBody CustomerAddress customerAdress ){
		Optional<Customer>customerData = customerService.findCustomerById(customerId);
		if(customerData.isPresent()) {
			Customer _customer = customerData.get();
			if(!_customer.isDeleted()) {
				customerService.createCustomerAddress(customerId, customerAdress);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
