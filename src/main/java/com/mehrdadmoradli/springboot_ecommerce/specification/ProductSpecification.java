package com.mehrdadmoradli.springboot_ecommerce.specification;

import com.mehrdadmoradli.springboot_ecommerce.entity.Product;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
	
	public static Specification<Product> hasName(String keyword){
		return (root, query, cb) -> keyword == null ? null :
			cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");		
	}
	
	public static Specification<Product> hasCategory(Long categoryId){
		return (root, query, cb) -> categoryId == null ? null :
			cb.equal(root.get("category_id"), categoryId);
	}
	
	public static Specification<Product> priceGreaterThan(BigDecimal minPrice){
		return (root, query, cb) -> minPrice == null ? null :
			cb.greaterThanOrEqualTo(root.get("price"), minPrice);
	}
	
	public static Specification<Product> priceLessThan(BigDecimal maxPrice){
		return (root, query, cb) -> maxPrice == null ? null :
			cb.lessThanOrEqualTo(root.get("price"), maxPrice);
	}

}
