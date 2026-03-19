package com.mehrdadmoradli.springboot_ecommerce.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CartItem> items = new ArrayList<>();
	
	@Column(nullable = false)
	private BigDecimal totalPrice = BigDecimal.ZERO;
	
	

		public Cart() {
		}


		public Cart(User user, List<CartItem> items, BigDecimal totalPrice) {
			this.user = user;
			this.items = items;
			this.totalPrice = totalPrice;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<CartItem> getItems() {
			return items;
		}

		public void setItems(List<CartItem> items) {
			this.items = items;
		}

		public BigDecimal getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
		}
		
		public BigDecimal calculateTotalPrice() {
			this.totalPrice = this.items.stream()
					.map(CartItem::getPrice)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			return this.totalPrice;
		}
		
		public String getUsername() {
			return this.user.getUsername();
		}
}
