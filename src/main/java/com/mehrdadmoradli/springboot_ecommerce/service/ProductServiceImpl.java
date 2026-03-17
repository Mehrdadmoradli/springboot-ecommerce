package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;
import com.mehrdadmoradli.springboot_ecommerce.entity.Category;
import com.mehrdadmoradli.springboot_ecommerce.repository.ProductRepository;
import com.mehrdadmoradli.springboot_ecommerce.repository.CategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository catRepository;
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Product addProduct(Product product, Long categoryId) {
		Category cat = catRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
		product.setCategory(cat);
		return productRepository.save(product);
	}
	
	@Override
	public Product getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		return product;
	}
	
	@Override 
	public List<Product> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products;
	}
	
	@Override
	public Product updateProduct(Product product, Long id, Long categoryId) {
		Category cat = catRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
		product.setCategory(cat);	
		Product productToBeUpdated = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		mapper.map(product, productToBeUpdated);
		return productRepository.save(productToBeUpdated);
	}
	
	@Override
	public void deleteProductById(Long id) {
		if (!productRepository.existsById(id)) {
			throw new RuntimeException("Product not found");
		}
		productRepository.deleteById(id);
	}
	
}
