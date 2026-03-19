package com.mehrdadmoradli.springboot_ecommerce.dto;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;

import java.math.BigDecimal;

public class CartItemResponseDto {
	
	private Long id;
	private Long cartId;
	private Product product;
	private Integer quantity;
	private BigDecimal price;
	
	

	    public CartItemResponseDto() {
	    }

	    public CartItemResponseDto(Long id, Long cartId, Product product, Integer quantity, BigDecimal price) {
	        this.id = id;
	        this.cartId = cartId;
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

	    public Long getCartId() {
	        return cartId;
	    }

	    public void setCartId(Long cartId) {
	        this.cartId = cartId;
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
	}

