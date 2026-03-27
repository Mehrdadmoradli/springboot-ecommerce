package com.mehrdadmoradli.springboot_ecommerce.dto;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;

import java.math.BigDecimal;

public class CartItemResponseDto {
	
	private Long cartId;
	private Product product;
	private Integer quantity;
	private BigDecimal price;
	
	

	    public CartItemResponseDto() {
	    }

	    public CartItemResponseDto(Long cartId, Product product, Integer quantity, BigDecimal price) {
	        this.cartId = cartId;
	        this.product = product;
	        this.quantity = quantity;
	        this.price = price;
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

