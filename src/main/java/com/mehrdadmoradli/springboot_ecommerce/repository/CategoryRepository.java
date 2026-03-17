package com.mehrdadmoradli.springboot_ecommerce.repository;

import com.mehrdadmoradli.springboot_ecommerce.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
