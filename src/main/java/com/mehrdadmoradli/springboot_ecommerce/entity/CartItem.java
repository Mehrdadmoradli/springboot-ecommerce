package com.mehrdadmoradli.springboot_ecommerce.entity;

import jakarta.persistence.*;
import java.math.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="cart_id", nullable = false)
	private Cart cart;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private BigDecimal price = new BigDecimal("0.0");
	

	public CartItem() {
	}


	public CartItem(Cart cart, Product product, Integer quantity, BigDecimal price) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void updatePrice() {
		this.price = this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
	}
	
	
}

