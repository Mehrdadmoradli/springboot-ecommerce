package com.mehrdadmoradli.springboot_ecommerce.controller;

import com.mehrdadmoradli.springboot_ecommerce.entity.Cart;
import com.mehrdadmoradli.springboot_ecommerce.entity.CartItem;
import com.mehrdadmoradli.springboot_ecommerce.service.CartServiceImpl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartServiceImpl cartService;
	
	@PostMapping("/item")
	public ResponseEntity<CartItem> addItem(@Valid @RequestParam Long productId, @Valid @RequestParam Integer quantity){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		CartItem item = cartService.addItem(username, productId, quantity);
		return ResponseEntity.ok(item);
	}
	
	@DeleteMapping("/item")
	public ResponseEntity<Cart> removeItem(@Valid @RequestParam Long cartItemId){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		Cart cart = cartService.removeItem(username, cartItemId);
		return ResponseEntity.ok(cart);
	}
	
	@PutMapping ("/item")
	public ResponseEntity<CartItem> updateItemQuantity(@Valid @RequestParam Long cartItemId, @Valid @RequestParam Integer newQuantity){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		CartItem item = cartService.updateItemQuantity(username, cartItemId, newQuantity);
		return ResponseEntity.ok(item);
	}
	
	@GetMapping
	public ResponseEntity<List<CartItem>> getAllItems(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		List<CartItem> items = cartService.getItems(username);
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/price")
	public ResponseEntity<BigDecimal> getTotalPrice(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		BigDecimal totalPrice = cartService.getTotalPrice(username);
		return ResponseEntity.ok(totalPrice);
	}
}
