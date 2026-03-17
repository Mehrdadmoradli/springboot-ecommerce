package com.mehrdadmoradli.springboot_ecommerce.controller;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;
import com.mehrdadmoradli.springboot_ecommerce.service.ProductServiceImpl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productService;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product, @RequestParam Long categoryId) {
		Product savedProduct = productService.addProduct(product, categoryId);
		return ResponseEntity.status(201).body(savedProduct);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable Long id, @RequestParam Long categoryId){
		Product updatedProduct = productService.updateProduct(product, id, categoryId);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		productService.deleteProductById(id);
		return ResponseEntity.ok("Product has been removed");
	}
	
}