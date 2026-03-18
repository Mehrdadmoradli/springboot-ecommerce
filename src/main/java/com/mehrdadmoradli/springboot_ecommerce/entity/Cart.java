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
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CartItem> items = new ArrayList<>();
	
	@Column(nullable = false)
	private BigDecimal totalPrice = BigDecimal.ZERO;
	
}
