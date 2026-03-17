package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
	
	Product addProduct(Product product, Long categoryId);
	Product getProductById(Long id);
	List<Product> getAllProducts();
	Product updateProduct(Product product, Long id, Long categoryId);
	void deleteProductById(Long id);
}
