package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;
import com.mehrdadmoradli.springboot_ecommerce.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository repository;
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Product addProduct(Product product) {
		return repository.save(product);
	}
	
	@Override
	public Product getProductById(Long id) {
		Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		return product;
	}
	
	@Override 
	public List<Product> getAllProducts(){
		List<Product> products = repository.findAll();
		return products;
	}
	
	@Override
	public Product updateProduct(Product product, Long id) {
		Product productToBeUpdated = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		mapper.map(productToBeUpdated, productToBeUpdated);
		return repository.save(productToBeUpdated);
	}
	
	@Override
	public void deleteProductById(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Product not found");
		}
		repository.deleteById(id);
	}
	
}
