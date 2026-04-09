package com.mehrdadmoradli.springboot_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mehrdadmoradli.springboot_ecommerce.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	void deleteByUsername(String username);
}
