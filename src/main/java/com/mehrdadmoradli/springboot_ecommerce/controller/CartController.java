package com.mehrdadmoradli.springboot_ecommerce.controller;

import com.mehrdadmoradli.springboot_ecommerce.entity.Cart;
import com.mehrdadmoradli.springboot_ecommerce.entity.CartItem;
import com.mehrdadmoradli.springboot_ecommerce.dto.CartResponseDto;
import com.mehrdadmoradli.springboot_ecommerce.dto.CartItemResponseDto;
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

import org.modelmapper.ModelMapper;

import jakarta.validation.Valid;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartServiceImpl cartService;
	@Autowired
	ModelMapper mapper;
	
	@PostMapping("/item")
	public ResponseEntity<CartItemResponseDto> addItem(@Valid @RequestParam Long productId, @Valid @RequestParam Integer quantity){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		CartItem item = cartService.addItem(username, productId, quantity);
		CartItemResponseDto itemDto = mapper.map(item, CartItemResponseDto.class);
		itemDto.setCartId(item.getCart().getId());
		return ResponseEntity.ok(itemDto);
	}
	
	@DeleteMapping("/item")
	public ResponseEntity<CartResponseDto> removeItem(@Valid @RequestParam Long cartItemId){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		Cart cart = cartService.removeItem(username, cartItemId);
		cart.calculateTotalPrice();
		CartResponseDto cartDto = mapper.map(cart, CartResponseDto.class);
		cartDto.setUsername(cart.getUsername());
		List<CartItemResponseDto> dtoItems = new ArrayList<>();
		for (CartItem item : cart.getItems()) {
			CartItemResponseDto itemDto = mapper.map(item, CartItemResponseDto.class);
			itemDto.setCartId(item.getCart().getId());
			dtoItems.add(itemDto);
		}
		cartDto.setItems(dtoItems);
		return ResponseEntity.ok(cartDto);
	}
	
	@PutMapping ("/item")
	public ResponseEntity<CartItemResponseDto> updateItemQuantity(@Valid @RequestParam Long cartItemId, @Valid @RequestParam Integer newQuantity){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		CartItem item = cartService.updateItemQuantity(username, cartItemId, newQuantity);
		CartItemResponseDto itemDto = mapper.map(item, CartItemResponseDto.class);
		itemDto.setCartId(item.getCart().getId());		
		return ResponseEntity.ok(itemDto);
	}
	
	@GetMapping
	public ResponseEntity<List<CartItemResponseDto>> getAllItems(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		List<CartItem> items = cartService.getItems(username);
		List<CartItemResponseDto> dtoItems = new ArrayList<>();
		for (CartItem item : items) {
			CartItemResponseDto itemDto = mapper.map(item, CartItemResponseDto.class);
			itemDto.setCartId(item.getCart().getId());;
			dtoItems.add(itemDto);
		}
		return ResponseEntity.ok(dtoItems);
	}
	
	@GetMapping("/price")
	public ResponseEntity<BigDecimal> getTotalPrice(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		BigDecimal totalPrice = cartService.getTotalPrice(username);
		return ResponseEntity.ok(totalPrice);
	}
	
}
