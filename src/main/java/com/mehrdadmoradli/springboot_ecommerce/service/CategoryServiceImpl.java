package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Category;
import com.mehrdadmoradli.springboot_ecommerce.repository.CategoryRepository;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository repository;
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Category addCategory(Category cat) {
		return repository.save(cat);
	}
	
	@Override
	public Category getCategoryById(Long id) {
		Category cat = repository.findById(id).orElseThrow(() -> new RuntimeException ("Category not found"));
		return cat;
		
	}
	
	@Override
	public List<Category> getAllCategories(){
		return repository.findAll();
	}
	
	@Override
	public Category updateCategory(Category cat, Long id) {
		Category categoryToBeUpdated = repository.findById(id).orElseThrow(() -> new RuntimeException ("Category not found"));
		mapper.map(cat, categoryToBeUpdated);
		return repository.save(categoryToBeUpdated);
		
	}
	
	@Override 
	public void deleteCategoryById(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Category not found");
		}
		repository.deleteById(id);
		
	}
	
}
