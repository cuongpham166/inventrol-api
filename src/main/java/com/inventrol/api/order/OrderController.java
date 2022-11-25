package com.inventrol.api.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventrol.api.order.orderpayment.OrderPaymentView;
import com.inventrol.api.order.ordershipping.OrderShippingView;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired 
	private OrderService orderService;

	@GetMapping("/order")
	public ResponseEntity<List<OrderView>> getAllOrders() {
		try {
			List<OrderView> orders = new ArrayList<OrderView>();
			orders = orderService.getAllOrders();
			if (orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order/payment")
	public ResponseEntity<List<OrderPaymentView>> getAllOrderPayments() {
		try {
			List<OrderPaymentView> orderPayments = new ArrayList<OrderPaymentView>();
			orderPayments = orderService.getAllOrderPayments();
			if (orderPayments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(orderPayments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/order/shipping")
	public ResponseEntity<List<OrderShippingView>> getAllOrderShipping() {
		try {
			List<OrderShippingView> orderShipping = new ArrayList<OrderShippingView>();
			orderShipping = orderService.getAllOrderShipping();
			if (orderShipping.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(orderShipping, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// @GetMapping("/order/id")
	// @PutMapping("/order/{orderId}/shipping/{shippingId}")
	// @PutMapping("/order/{orderId}/payment/{paymentId}")
	
}
