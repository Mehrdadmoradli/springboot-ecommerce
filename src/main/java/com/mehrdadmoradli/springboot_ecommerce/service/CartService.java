package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Cart;
import com.mehrdadmoradli.springboot_ecommerce.entity.CartItem;

import java.math.*;
import java.util.List;

public interface CartService {
	
	CartItem addItem(String username, Long productId, Integer quantity);
	Cart removeItem(String username, Long cartItemId);
	CartItem updateItemQuantity(String username, Long cartItemId, Integer newQuantity);
	BigDecimal getTotalPrice(String username);
	List<CartItem> getItems(String username);
}
