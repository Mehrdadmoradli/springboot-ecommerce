package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.math.BigDecimal;

public interface ProductService {
	
	Product addProduct(Product product, Long categoryId);
	Product getProductById(Long id);
	List<Product> getAllProducts();
	Product updateProduct(Product product, Long id, Long categoryId);
	void deleteProductById(Long id);
	Page<Product> searchProduct(
			String keyword,
			Long categoryId,
			BigDecimal minPrice,
			BigDecimal maxPrice,
			Pageable pageable);
}
