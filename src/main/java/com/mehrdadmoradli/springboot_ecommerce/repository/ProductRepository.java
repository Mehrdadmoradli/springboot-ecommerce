package com.mehrdadmoradli.springboot_ecommerce.repository;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
