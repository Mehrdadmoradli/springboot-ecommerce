package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.enums.OrderStatus;
import com.mehrdadmoradli.springboot_ecommerce.entity.*;
import com.mehrdadmoradli.springboot_ecommerce.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;



@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired 
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;
	

	@Transactional
	@Override 
	public Order createOrder(String userName) {
		
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			Product product = cartItem.getProduct();
			if(product.getStock() < cartItem.getQuantity()) {
				throw new RuntimeException("Not enough stock");
			}
			orderItem.setOrder(order);
			orderItem.setProductId(product.getId());
			orderItem.setProductName(product.getName());
			orderItem.setPriceAtPurchase(product.getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			product.setStock(product.getStock() - cartItem.getQuantity());
			productRepository.save(product);
			orderItem.updatePrice();
			orderItems.add(orderItem);
		}
		
		cart.getItems().clear();
		cartRepository.save(cart);
		
		order.setUser(user);
		order.setStatus(OrderStatus.CREATED); 
		order.setAdress(user.getAddresses().get(0));
		order.setItems(orderItems);
		order.calculateNetPrice();
		order.calculateVatAmount();
		order.calculateTotalPrice();
		
		return orderRepository.save(order);
	}

	@Override
	public Order cancleOrder(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		for (OrderItem item : order.getItems()) {
			Product product = productRepository.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
			product.setStock(product.getStock() + item.getQuantity());
		}
		order.setStatus(OrderStatus.CANCELED);
		order.setCanceledAt();
		return orderRepository.save(order);
	}
	@Override
	public Order orderPayment(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		int num = (int) Math.random()*10;
		if (num < 8) {
			order.setStatus(OrderStatus.PAID);
			order.setPaidAt();
			return orderRepository.save(order);
		}
		System.out.println("Payment failed.");
		return order;
	}
	
	@Override
	public Order markAsShipped(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setStatus(OrderStatus.SHIPPED);
		order.setShippedAt();
		return orderRepository.save(order);
	}
	
	@Override
	public Order markAsDelivered(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setStatus(OrderStatus.DELIVERED);
		order.setDeliveredAt();
		return orderRepository.save(order);
	}	
	
}





