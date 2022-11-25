package com.inventrol.api.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.inventrol.api.customer.Customer;
import com.inventrol.api.order.orderaddress.OrderAddress;
import com.inventrol.api.order.orderhistory.OrderHistory;
import com.inventrol.api.order.orderitem.OrderItem;
import com.inventrol.api.order.orderpayment.OrderPayment;
import com.inventrol.api.order.ordershipping.OrderShipping;

@Entity
@Table(name = "orders")
public class Order {
	public Order(OrderPayment payment, OrderShipping shipping, Set<OrderHistory> orderhistory, Set<OrderItem> orderitem,
			Customer customer, OrderAddress shippingAddress, OrderAddress billingAddress, BigDecimal subtotal,
			BigDecimal total, BigDecimal shippingCost, BigDecimal discount, BigDecimal vat, String notice,
			boolean deleted, LocalDateTime createdOn, String createdBy) {
		super();
		this.payment = payment;
		this.shipping = shipping;
		this.orderhistory = orderhistory;
		this.orderitem = orderitem;
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.subtotal = subtotal;
		this.total = total;
		this.shippingCost = shippingCost;
		this.discount = discount;
		this.vat = vat;
		this.notice = notice;
		this.deleted = deleted;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

	public Order() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="payment_id", nullable=false)
	private OrderPayment payment;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="shipping_id", nullable=false)
	private OrderShipping shipping;
	
	@OneToMany(mappedBy ="order",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<OrderHistory>orderhistory = new HashSet<OrderHistory>();
	
	@OneToMany(mappedBy ="order",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<OrderItem>orderitem = new HashSet<OrderItem>();
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	@Embedded
	@AttributeOverrides({
		    @AttributeOverride(name="streetName", column=@Column(name="ship_streetName")),
		    @AttributeOverride(name="streetNumber", column=@Column(name="ship_streetNumber")),
		    @AttributeOverride(name="additionalAddressLine", column=@Column(name="ship_additionalAddressLine")),
		    @AttributeOverride(name="postcode", column=@Column(name="ship_postcode")),
		    @AttributeOverride(name="city", column=@Column(name="ship_city")),
		    @AttributeOverride(name="country", column=@Column(name="ship_country")),
		    @AttributeOverride(name="notice", column=@Column(name="ship_notice")),
	})  
	private OrderAddress shippingAddress;
	
	@Embedded 
	@AttributeOverrides({
		    @AttributeOverride(name="streetName", column=@Column(name="bill_streetName")),
		    @AttributeOverride(name="streetNumber", column=@Column(name="bill_streetNumber")),
		    @AttributeOverride(name="additionalAddressLine", column=@Column(name="bill_additionalAddressLine")),
		    @AttributeOverride(name="postcode", column=@Column(name="bill_postcode")),
		    @AttributeOverride(name="city", column=@Column(name="bill_city")),
		    @AttributeOverride(name="country", column=@Column(name="bill_country")),
		    @AttributeOverride(name="notice", column=@Column(name="bill_notice")),
	}) 
	private OrderAddress billingAddress;
	
	
	@Column(name="subtotal",precision=10, scale=2)
	private BigDecimal subtotal;
	
	@Column(name="total",precision=10, scale=2)
	private BigDecimal total;
	
	@Column(name="shipping_cost",precision=10, scale=2)
	private BigDecimal shippingCost;
	
	@Column(name="discount",precision=10, scale=2)
	private BigDecimal discount;
	
	@Column(name="vat",precision=10, scale=2)
	private BigDecimal vat;
	
	@Column(name = "notice")
	private String notice;
	
	@Column(name = "is_deleted")
	@Value("false")
	private boolean deleted;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
 
    @Column(name = "created_by")
    private String createdBy;
     

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }
 
	public long getId() {
		return id;
	}

	public Set<OrderItem> getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(Set<OrderItem> orderitem) {
		this.orderitem = orderitem;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}


	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public OrderAddress getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(OrderAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public OrderAddress getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(OrderAddress billingAddress) {
		this.billingAddress = billingAddress;
	}


	public LocalDateTime getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Set<OrderHistory> getOrderhistory() {
		return orderhistory;
	}

	public void setOrderhistory(Set<OrderHistory> orderhistory) {
		this.orderhistory = orderhistory;
	}

	public BigDecimal getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderPayment getPayment() {
		return payment;
	}

	public void setPayment(OrderPayment payment) {
		this.payment = payment;
	}

	public OrderShipping getShipping() {
		return shipping;
	}

	public void setShipping(OrderShipping shipping) {
		this.shipping = shipping;
	}
}
