package com.mehrdadmoradli.springboot_ecommerce.dto;

import java.util.List;
import java.math.BigDecimal;

public class CartResponseDto {
	
	private String username;
	private List<CartItemResponseDto> items;
	private BigDecimal totalPrice;

    public CartResponseDto() {
    }

    public CartResponseDto(String username, List<CartItemResponseDto> items, BigDecimal totalPrice) {
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
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

