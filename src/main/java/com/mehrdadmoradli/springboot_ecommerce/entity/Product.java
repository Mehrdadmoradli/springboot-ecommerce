package com.mehrdadmoradli.springboot_ecommerce.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private Integer stock;
	
	@Column(nullable = false)
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	 public Product() {
	    }
	 
	 public Product(Long id, String name, BigDecimal price, Integer stock, boolean isActive, Category category) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        this.stock = stock;
	        this.isActive = isActive;
	        this.category = category;
	    }


	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public BigDecimal getPrice() {
	        return price;
	    }

	    public void setPrice(BigDecimal price) {
	        this.price = price;
	    }

	    public Integer getStock() {
	        return stock;
	    }

	    public void setStock(Integer stock) {
	        this.stock = stock;
	    }

	    public boolean isActive() {
	        return isActive;
	    }

	    public void setActive(boolean active) {
	        isActive = active;
	    }

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }
}
