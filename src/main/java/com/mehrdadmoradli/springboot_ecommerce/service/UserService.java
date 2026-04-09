package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.entity.User;
import com.mehrdadmoradli.springboot_ecommerce.entity.Address;
import com.mehrdadmoradli.springboot_ecommerce.dto.*;
import java.util.List;

public interface UserService {
	
	User registerUser(UserRegistrationDto userDto);
	User registerAdmin(UserRegistrationDto userDto);
	User updateUser(UserUpdateDto userdto, String username);
	User getUserById(Long id);
	User addNewAddress(Address address, String username);
	List<User> getAllUsers();
	void deleteUser(String username);
	
}
