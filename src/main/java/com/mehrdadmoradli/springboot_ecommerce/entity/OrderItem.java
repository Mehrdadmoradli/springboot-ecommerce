package com.mehrdadmoradli.springboot_ecommerce.entity;

import jakarta.persistence.*;
import java.math.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private BigDecimal priceAtPurchase;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private BigDecimal price = BigDecimal.ZERO;
	

		public OrderItem() {
		}


		public OrderItem(Order order, Long productId, String productName, BigDecimal priceAtPurchase, Integer quantity, BigDecimal price) {
			this.order = order;
			this.productId = productId;
			this.productName = productName;
			this.priceAtPurchase = priceAtPurchase;
			this.quantity = quantity;
			this.price = price;
		}

		public Long getId() {
			return id;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Long getProductId() {
			return this.productId;
		}
		
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		
		public String getProductName() {
			return this.productName;
		}
		
		public void setProductName(String productName) {
			this.productName = productName;
		}
		
		public BigDecimal getPriceAtPurchase() {
			return this.priceAtPurchase;
		}
		
		public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
			this.priceAtPurchase = priceAtPurchase;
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
		
		public BigDecimal updatePrice() {
			this.price = this.priceAtPurchase.multiply(BigDecimal.valueOf(this.quantity));
			return this.price;
		}
}

