package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.*;
import com.mehrdadmoradli.springboot_ecommerce.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Iterator;


@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;
	

	@Override
	public CartItem addItem(String username, Long productId, Integer quantity) {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
		CartItem item = new CartItem(cart, product, quantity);
		item.updatePrice();
		cart.getItems().add(item);
		item.setCart(cart);
		cartRepository.save(cart);
		
		return item;
		
	}

	@Override
	public Cart removeItem(String username, Long cartItemId) {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
		Iterator<CartItem> iter = cart.getItems().iterator();
		while(iter.hasNext()) {
			CartItem item = iter.next();
			if (item.getId().equals(cartItemId)) {
				iter.remove();
				return cartRepository.save(cart);
			}
		}
		
		throw new RuntimeException("Item not found");
	}

	@Override
	public CartItem updateItemQuantity(String username, Long cartItemId, Integer newQuantity) {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));

		Iterator<CartItem> iter = cart.getItems().iterator();
		while(iter.hasNext()) {
			CartItem item = iter.next();
			if (item.getId().equals(cartItemId)) {
				item.setQuantity(newQuantity);
				cartRepository.save(cart);
				return item;
			}
		}
		
		throw new RuntimeException("Item not found");
	}

	@Override
	public BigDecimal getTotalPrice(String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
		
		return cart.calculateTotalPrice();
	}

	@Override
	public List<CartItem> getItems(String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
		
		return cart.getItems();
		
	}

}
