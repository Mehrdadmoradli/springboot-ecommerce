package com.mehrdadmoradli.springboot_ecommerce.repository;

import com.mehrdadmoradli.springboot_ecommerce.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
		public Cart findByUserId(Long id);
}
