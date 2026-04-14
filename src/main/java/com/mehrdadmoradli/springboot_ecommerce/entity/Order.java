package com.mehrdadmoradli.springboot_ecommerce.entity;

import com.mehrdadmoradli.springboot_ecommerce.enums.OrderStatus;
import com.mehrdadmoradli.springboot_ecommerce.enums.VatRate;

import jakarta.persistence.*;
import java.math.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	private BigDecimal netPrice;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VatRate vatRate = VatRate.STANDARD;
	
	@Column(nullable = false)
	private BigDecimal vatAmount;
	
	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	private LocalDateTime paidAt;
	
	private LocalDateTime shippedAt;
	
	private LocalDateTime deliveredAt;
	
	private LocalDateTime canceledAt;
	
	
	@Column(nullable = false)
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address adress;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> items = new ArrayList<>();
	
	public Order() {
	}

	public Order(User user, OrderStatus status, Address adress, List<OrderItem> items) {
		this.user = user;
		this.status = status;
		this.adress = adress;
		this.items = items;
	}


	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}
	
	public BigDecimal getVatRate() {
		return this.vatRate.getRate();
	}
	
	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}
	
	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	public LocalDateTime getPaidAt() {
		return this.paidAt;
	}
	
	public void setPaidAt() {
		this.paidAt = LocalDateTime.now();
	}
	
	public LocalDateTime getShippedAt() {
		return this.shippedAt;
	}
	
	public void setShippedAt() {
		this.shippedAt = LocalDateTime.now();
	}
	
	public LocalDateTime getDeliveredAt() {
		return this.deliveredAt;
	}
	
	public void setDeliveredAt() {
		this.deliveredAt = LocalDateTime.now();
	}
	
	public LocalDateTime getCanceledAt() {
		return this.canceledAt;
	}
	
	public void setCanceledAt() {
		this.canceledAt = LocalDateTime.now();
	}
	
	public OrderStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Address getAdress() {
		return this.adress;
	}
	
	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public BigDecimal calculateNetPrice() {
		this.netPrice = this.items.stream()
				.map(OrderItem::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return this.netPrice;
	}
	
	public BigDecimal calculateVatAmount() {
		this.vatAmount = this.netPrice.multiply(this.vatRate.getRate());
		return this.vatAmount;
	}
	

	public BigDecimal calculateTotalPrice() {
		this.totalPrice = this.netPrice.add(this.vatAmount);
		return this.totalPrice;
	}
	
}
