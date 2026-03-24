package com.mehrdadmoradli.springboot_ecommerce.entity;

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
	private BigDecimal totalPrice;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private String adress;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> items = new ArrayList<>();
	
	
	public Order() {
	}

	public Order(User user, BigDecimal totalPrice, LocalDateTime createdAt, String status, String adress, List<OrderItem> items) {
		this.user = user;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAdress() {
		return this.adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}
	
}
