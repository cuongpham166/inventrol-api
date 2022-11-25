package com.inventrol.api.order;

import java.util.List;

import com.inventrol.api.order.orderpayment.OrderPaymentView;
import com.inventrol.api.order.ordershipping.OrderShippingView;

public interface OrderInterface {
	public List<OrderView>getAllOrders();
	public List<OrderPaymentView>getAllOrderPayments();
	public List<OrderShippingView>getAllOrderShipping();
}
