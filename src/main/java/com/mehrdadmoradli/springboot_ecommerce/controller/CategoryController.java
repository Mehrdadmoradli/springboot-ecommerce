package com.mehrdadmoradli.springboot_ecommerce.controller;

import com.mehrdadmoradli.springboot_ecommerce.entity.Category;
import com.mehrdadmoradli.springboot_ecommerce.service.CategoryServiceImpl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl catService;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category cat) {
		Category savedCat = catService.addCategory(cat);
		return ResponseEntity.status(201).body(savedCat);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Category> getCategory(@PathVariable Long id){
		Category cat = catService.getCategoryById(id);
		return ResponseEntity.ok(cat);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = catService.getAllCategories();
		return ResponseEntity.ok(categories);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category cat, @PathVariable Long id){
		Category updatedCat = catService.updateCategory(cat, id);
		return ResponseEntity.ok(updatedCat);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id){
		catService.deleteCategoryById(id);
		return ResponseEntity.ok("Category has been removed");
	}
	
}
