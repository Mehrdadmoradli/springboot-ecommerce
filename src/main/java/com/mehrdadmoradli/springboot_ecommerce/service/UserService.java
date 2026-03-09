package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.User;
import com.mehrdadmoradli.springboot_ecommerce.dto.*;
import java.util.List;

public interface UserService {
	
	User registerUser(UserRegistrationDto userDto);
	User registerAdmin(UserRegistrationDto userDto);
	User updateUser(UserRegistrationDto userdto, Long id);
	User getUserById(Long id);
	List<User> getAllUsers();
	void deleteUser(Long id);
	
}
