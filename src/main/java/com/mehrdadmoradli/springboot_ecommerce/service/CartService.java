package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Cart;
import com.mehrdadmoradli.springboot_ecommerce.entity.CartItem;

import java.math.*;
import java.util.List;

public interface CartService {
	
	Cart addItem(Long userId, Long productId, Integer quantity);
	Cart removeItem(Long userId, Long cartItemId);
	Cart updateItemQuantity(Long userId, Long cartItemId, Integer newQuantity);
	BigDecimal getTotalPrice(Long userId);
	List<CartItem> getItems(Long userId);
}
