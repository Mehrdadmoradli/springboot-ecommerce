package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
	
	Category addCategory(Category cat);
	Category getCategoryById(Long id);
	List<Category> getAllCategories();
	Category updateCategory(Category cat, Long id);
	void deleteCategoryById(Long id);
}
