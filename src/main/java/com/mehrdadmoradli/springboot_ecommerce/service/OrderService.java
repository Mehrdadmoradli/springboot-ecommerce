package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Order;

public interface OrderService {
	
	Order createOrder(String userName);
	Order cancleOrder(Long orderId);
}
