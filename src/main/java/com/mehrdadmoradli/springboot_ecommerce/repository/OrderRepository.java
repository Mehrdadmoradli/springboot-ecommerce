package com.mehrdadmoradli.springboot_ecommerce.repository;

import com.mehrdadmoradli.springboot_ecommerce.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Optional<Order> findByUserId(Long id);
}
