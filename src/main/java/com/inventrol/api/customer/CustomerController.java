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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.auth.MessageResponse;
import com.inventrol.api.customer.customeraddress.CustomerAddress;
import com.inventrol.api.customer.customeraddress.CustomerAddressDetailView;
import com.inventrol.api.customer.customeraddress.CustomerAddressView;
import com.inventrol.api.order.Order;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepo;
	
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

	@GetMapping("/customer/{id}/address")
	public ResponseEntity<CustomerAddressView>getAllAddressesByCustomerId(@PathVariable("id") long id){
		Optional<Customer> customerData = customerService.findCustomerById(id);
		if(customerData.isPresent()) {
			Customer _customer = customerData.get();
			if(_customer.isDeleted() == false) {
				CustomerAddressView _customerAddress= customerService.getAddressByCustomerId(id);
				return new ResponseEntity<>(_customerAddress, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	/*@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer newCustomer){
		try{
			if(customerRepo.existsCustomerByName(newCustomer.getName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
			}
			customerService.createCustomer(newCustomer);
			return ResponseEntity.ok().body(new MessageResponse("Success:  A new customer has been created"));
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  Internal Server Error"));
		}
	}*/
	
	@PutMapping("/customer/{customerId}")
	public ResponseEntity<?>updateCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer updatedCustomer){
		Optional<Customer>customerData = customerService.findCustomerById(customerId);
		if(customerData.isPresent()) {
			Customer foundCustomer = customerData.get();
			if(foundCustomer.isDeleted()==false) {
				customerService.updateCustomer(customerId, updatedCustomer);
				return ResponseEntity.ok().body(new MessageResponse("Success: Customer has been updated"));
			}
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody NewCustomerPayload newCustomerPayload){
		try{
			if(customerRepo.existsCustomerByName(newCustomerPayload.getCustomer().getName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: This name already exists"));
			}
			customerService.createCustomer(newCustomerPayload);
			return ResponseEntity.ok().body(new MessageResponse("Success:  A new customer has been created"));
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse("Error:  Internal Server Error"));
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

	@GetMapping("/customer/{customerId}/address/{addressId}")
	public ResponseEntity<CustomerAddressDetailView>getAddressDetailByCustomerId(@PathVariable("customerId") long customerId,@PathVariable("addressId") long addressId){
		Optional<Customer> customerData = customerService.findCustomerById(customerId);
		Optional<CustomerAddress> customerAddressData = customerService.findCustomerAddressById(addressId);
		if(customerData.isPresent() && customerAddressData.isPresent()) {
			CustomerAddress foundCustomerAddress  = customerAddressData.get();
			if(foundCustomerAddress.getCustomer().getId() == customerId) {
				CustomerAddressDetailView customerAddressDetail = customerService.getCustomerAddressDetailById(addressId);
				return new ResponseEntity<>(customerAddressDetail, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/customer/{customerId}/address/{addressId}")
	public ResponseEntity<?>updateCustomerAddress(@PathVariable("customerId") long customerId,@PathVariable("addressId") long addressId, @RequestBody CustomerAddress updatedCustomerAddress){
		Optional<Customer> customerData = customerService.findCustomerById(customerId);
		Optional<CustomerAddress> customerAddressData = customerService.findCustomerAddressById(addressId);
		if(customerData.isPresent() && customerAddressData.isPresent()) {
			CustomerAddress foundCustomerAddress = customerAddressData.get();
			if(foundCustomerAddress.getCustomer().getId() == customerId) {
				customerService.updateCustomerAddress(customerId, addressId, updatedCustomerAddress);
				return ResponseEntity.ok().body(new MessageResponse("Success: Customer's address has been updated"));
			}else {
				return ResponseEntity.notFound().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/customer/{customerId}/address/{addressId}/primary")
	public ResponseEntity<?>setPrimaryAddress(@PathVariable("customerId") long customerId,@PathVariable("addressId") long addressId){
		Optional<Customer> customerData = customerService.findCustomerById(customerId);
		Optional<CustomerAddress> customerAddressData = customerService.findCustomerAddressById(addressId);
		if(customerData.isPresent() && customerAddressData.isPresent()) {
			CustomerAddress foundCustomerAddress = customerAddressData.get();
			if(foundCustomerAddress.getCustomer().getId() == customerId) {
				customerService.setPrimaryAddress(customerId, addressId);
				return ResponseEntity.ok().body(new MessageResponse("Success: Primary Address has been set"));
			}else {
				return ResponseEntity.notFound().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//@GetMapping("/customer/{customerId}/order")
	//@GetMapping("/customer/{customerId}/order/add")
	@PostMapping("/customer/{customerId}/order/add")
	public ResponseEntity<?> saveNewOrder(@PathVariable("customerId") long customerId, @RequestBody Order newOrder){
		try{
			Optional<Customer>customerData = customerService.findCustomerById(customerId);
			if(customerData.isPresent()) {
				customerService.saveNewOrder(customerId, newOrder);
				return ResponseEntity.ok().body(new MessageResponse("Success:  A new order has been placed"));
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
		}
	}
	
	
	
	
}
