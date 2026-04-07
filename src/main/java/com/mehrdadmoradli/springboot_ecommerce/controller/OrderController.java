package com.mehrdadmoradli.springboot_ecommerce.controller;

import com.mehrdadmoradli.springboot_ecommerce.dto.OrderResponseDto;
import com.mehrdadmoradli.springboot_ecommerce.entity.Order;
import com.mehrdadmoradli.springboot_ecommerce.entity.OrderItem;
import com.mehrdadmoradli.springboot_ecommerce.dto.OrderItemResponseDto;
import com.mehrdadmoradli.springboot_ecommerce.service.OrderServiceImpl;
import com.mehrdadmoradli.springboot_ecommerce.enums.OrderStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderServiceImpl orderService;
	@Autowired
	ModelMapper mapper;
	
	@PostMapping("/checkout")
	public ResponseEntity<OrderResponseDto> checkout(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		Order order = orderService.createOrder(username);
		OrderResponseDto orderDto = mapper.map(order, OrderResponseDto.class);
		orderDto.setUsername(username);
		List<OrderItemResponseDto> dtoItems = new ArrayList<>();
		for (OrderItem item : order.getItems()) {
			OrderItemResponseDto itemDto = mapper.map(item, OrderItemResponseDto.class);
			itemDto.setOrderId(order.getId());
			dtoItems.add(itemDto);
		}
		orderDto.setItems(dtoItems);
		return ResponseEntity.ok(orderDto);
	}
	
	@PutMapping
	public ResponseEntity<OrderResponseDto> cancleOrder(@RequestParam Long orderId){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		Order order = orderService.cancleOrder(orderId);
		OrderResponseDto orderDto = mapper.map(order, OrderResponseDto.class);
		orderDto.setUsername(username);
		List<OrderItemResponseDto> dtoItems = new ArrayList<>();
		for (OrderItem item : order.getItems()) {
			OrderItemResponseDto itemDto = mapper.map(item, OrderItemResponseDto.class);
			itemDto.setOrderId(order.getId());
			dtoItems.add(itemDto);
		}
		orderDto.setItems(dtoItems);
		return ResponseEntity.ok(orderDto);
	}
	
	@PostMapping("/payment")
	public ResponseEntity<OrderStatus> orderPayment(@RequestParam Long orderId){
		Order order = orderService.orderPayment(orderId);
		return ResponseEntity.ok(order.getStatus());
	}
}
