package com.mehrdadmoradli.springboot_ecommerce.repository;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
