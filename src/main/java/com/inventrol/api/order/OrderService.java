package com.inventrol.api.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventrol.api.order.orderpayment.OrderPaymentRepository;
import com.inventrol.api.order.orderpayment.OrderPaymentView;
import com.inventrol.api.order.ordershipping.OrderShippingRepository;
import com.inventrol.api.order.ordershipping.OrderShippingView;

@Service
public class OrderService implements OrderInterface {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired 
	private OrderPaymentRepository orderpaymentRepo;
	
	@Autowired
	private OrderShippingRepository ordershippingRepo;
	
	public List<OrderView>getAllOrders(){
		List<OrderView>orders = new ArrayList<OrderView>();
		orderRepo.findAllProjectedByOrderByIdAsc(OrderView.class).forEach(orders::add);
		List<OrderView> results = orders.stream().filter(res -> res.isDeleted() == false).collect(Collectors.toList());
		return results;
	}
	
	public List<OrderPaymentView>getAllOrderPayments(){
		List<OrderPaymentView>orderPayments = new ArrayList<OrderPaymentView>();
		orderpaymentRepo.findAllProjectedByOrderByIdAsc(OrderPaymentView.class).forEach(orderPayments::add);
		List<OrderPaymentView> results = orderPayments.stream().filter(res -> res.isDeleted() == false).collect(Collectors.toList());
		return results;
	}
	
	public List<OrderShippingView>getAllOrderShipping(){
		List<OrderShippingView>orderShipping = new ArrayList<OrderShippingView>();
		ordershippingRepo.findAllProjectedByOrderByIdAsc(OrderShippingView.class).forEach(orderShipping::add);
		List<OrderShippingView> results = orderShipping.stream().filter(res -> res.isDeleted() == false).collect(Collectors.toList());
		return results;
	}
}
