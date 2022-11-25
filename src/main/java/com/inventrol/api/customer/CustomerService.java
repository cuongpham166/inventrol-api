package com.inventrol.api.customer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.customer.customeraddress.CustomerAddress;
import com.inventrol.api.customer.customeraddress.CustomerAddressDetailView;
import com.inventrol.api.customer.customeraddress.CustomerAddressRepository;
import com.inventrol.api.customer.customeraddress.CustomerAddressView;
import com.inventrol.api.order.Order;
import com.inventrol.api.order.OrderRepository;
import com.inventrol.api.order.orderhistory.OrderHistory;
import com.inventrol.api.order.orderhistory.OrderHistoryRepository;
import com.inventrol.api.order.orderitem.OrderItem;
import com.inventrol.api.order.orderpayment.OrderPayment;
import com.inventrol.api.order.orderpayment.OrderPaymentRepository;
import com.inventrol.api.order.ordershipping.OrderShipping;
import com.inventrol.api.order.ordershipping.OrderShippingRepository;
import com.inventrol.api.product.Product;
import com.inventrol.api.product.ProductRepository;


@Service
public class CustomerService implements CustomerInterface{

	private BigDecimal subtotal = BigDecimal.ZERO;
	private BigDecimal totalDiscount = BigDecimal.ZERO;
	private BigDecimal totalVAT = BigDecimal.ZERO;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerAddressRepository customerAddressRepo;
	
	@Autowired 
	private ProductRepository productRepo;
	
	@Autowired 
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderPaymentRepository orderPaymentRepo;
	
	@Autowired
	private OrderShippingRepository orderShippingRepo;
	
	@Autowired
	private OrderHistoryRepository orderHistoryRepo;

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
	
	public void setPrimaryAddress (long customerId, long addressId) {
		Customer foundCustomer = findCustomerById(customerId).get();
		List<CustomerAddress> addressList = customerAddressRepo.findAllByCustomerId(customerId);
		for (CustomerAddress address : addressList) {
			if(address.getId() == addressId) {
				address.setPrimary(true);
			}else {
				address.setPrimary(false);
			}
			address.setAdditionalAddressLine(address.getAdditionalAddressLine());
			address.setCity(address.getCity());
			address.setCountry(address.getCountry());
			address.setNotice(address.getNotice());
			address.setPostcode(address.getPostcode());
			address.setStreetName(address.getStreetName());
			address.setStreetNumber(address.getStreetNumber());
			
			foundCustomer.getCustomeradress().add(address);
			address.setCustomer(foundCustomer);
			customerAddressRepo.save(address);		
		}
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
		_customerAddress.setPrimary(updatedCustomerAddress.isPrimary());
		_customerAddress.setCustomer(foundCustomer);
		
		customerAddressRepo.save(_customerAddress);
	}
	
	public BigDecimal getSubtotal (Set<OrderItem>orderitems) {
		orderitems.forEach(item ->{
			Product foundProduct = productRepo.findById(item.getProduct().getId()).get();
			BigDecimal itemPrice = foundProduct.getRetailPrice();
			int itemQuantity = item.getQuantity();
			BigDecimal itemCost = BigDecimal.ZERO;
			
			itemCost = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
			subtotal=subtotal.add(itemCost);
		});
		return subtotal;
	}
	
	public BigDecimal getTotalDiscount (Set<OrderItem>orderitems) {
		orderitems.forEach(item ->{
			Product foundProduct = productRepo.findById(item.getProduct().getId()).get();
			if(foundProduct.getDiscount().getDiscountPercent() > 0) {
				int itemQuantity = item.getQuantity();
				int discountValue = foundProduct.getDiscount().getDiscountPercent();
				BigDecimal itemPrice = foundProduct.getRetailPrice();
				BigDecimal discountPercent = BigDecimal.valueOf(discountValue).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
				BigDecimal totalDiscountValueItem = BigDecimal.ZERO;
				
				//total discount per item
				totalDiscountValueItem = (itemPrice.multiply(discountPercent)).multiply(BigDecimal.valueOf(itemQuantity));
				totalDiscount = totalDiscount.add(totalDiscountValueItem);
			}
		});
		return totalDiscount;
	}
	
	public BigDecimal getTotalVat (Set<OrderItem>orderitems) {
		orderitems.forEach(item ->{
			Product foundProduct = productRepo.findById(item.getProduct().getId()).get();
			
			int itemQuantity = item.getQuantity();
			int vatValue = foundProduct.getVat();
			BigDecimal itemPrice = foundProduct.getRetailPrice();
			BigDecimal vatPercent =  BigDecimal.valueOf(vatValue).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
			BigDecimal totalVatItem = BigDecimal.ZERO;
			
			//total vat per item
			totalVatItem = (itemPrice.multiply(vatPercent)).multiply(BigDecimal.valueOf(itemQuantity));
			totalVAT = totalVAT.add(totalVatItem);
		});
		return totalVAT;
	}
	
	public void saveNewOrder (long customerId, Order newOrder) {
		Customer foundCustomer = findCustomerById(customerId).get();
		
		Set<OrderItem>orderItems = newOrder.getOrderitem();
		BigDecimal shippingCost = newOrder.getShippingCost();
		BigDecimal subtotalValue = getSubtotal(orderItems);
		BigDecimal vatValue = getTotalVat(orderItems);
		BigDecimal discountValue = getTotalDiscount(orderItems);
		
		BigDecimal totalValue = (subtotalValue.add(vatValue).add(shippingCost)).subtract(discountValue);
		
		newOrder.setBillingAddress(newOrder.getBillingAddress());
		newOrder.setCustomer(foundCustomer);
		newOrder.setDiscount(discountValue);
		newOrder.setNotice(newOrder.getNotice());
		newOrder.setShippingAddress(newOrder.getShippingAddress());
		newOrder.setShippingCost(shippingCost);
		newOrder.setSubtotal(subtotalValue);
		newOrder.setTotal(totalValue);
		newOrder.setVat(vatValue);
		
		orderItems.forEach(item ->{
			item.setOrder(newOrder);
			newOrder.getOrderitem().add(item);
		});
		
		OrderPayment newOrderPayment = new OrderPayment();
		newOrderPayment.setTotal(totalValue);
		newOrderPayment.setOrder(newOrder);
		newOrder.setPayment(newOrderPayment);
		orderPaymentRepo.save(newOrderPayment);
		Order savedOrder = orderRepo.save(newOrder);
		
		OrderShipping newOrderShipping = new OrderShipping();
		newOrderShipping.setStatus("Processing");
		newOrderShipping.setOrder(savedOrder);
		savedOrder.setShipping(newOrderShipping);
		orderShippingRepo.save(newOrderShipping);
		orderRepo.save(savedOrder);
		
		OrderHistory newOrderHistory = new OrderHistory();
		newOrderHistory.setStatus("Order placed");
		newOrderHistory.setOrder(savedOrder);
		savedOrder.getOrderhistory().add(newOrderHistory);
		orderHistoryRepo.save(newOrderHistory);
		
		orderItems.forEach(item -> {
			Optional <Product> productData = productRepo.findById(item.getProduct().getId());
			if(productData.isPresent()) {
				Product foundProduct = productData.get();
				foundProduct.getOrderItem().add(item);
				item.setProduct(foundProduct);
				productRepo.save(foundProduct);
			}
		});
	}
}
