package com.mehrdadmoradli.springboot_ecommerce.dto;

import java.util.List;
import java.math.BigDecimal;

public class CartResponseDto {
	
	private Long id;
	private String username;
	private List<CartItemResponseDto> items;
	private BigDecimal totalPrice;

    public CartResponseDto() {
    }

    public CartResponseDto(Long id, String username, List<CartItemResponseDto> items, BigDecimal totalPrice) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CartItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponseDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

