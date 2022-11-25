package com.inventrol.api.purchase.purchaseitem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.inventrol.api.product.Product;
import com.inventrol.api.purchase.Purchase;

@Entity
@Table(name = "purchase_item")
public class PurchaseItem {
	public PurchaseItem() {
		super();
	}

	public PurchaseItem(Product product, Purchase purchase, int quantity) {
		super();
		this.product = product;
		this.purchase = purchase;
		this.quantity = quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="purchase_id", nullable=false)
	private Purchase purchase;
	
	@Column(name="quantity")
	private int quantity;

	public long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
