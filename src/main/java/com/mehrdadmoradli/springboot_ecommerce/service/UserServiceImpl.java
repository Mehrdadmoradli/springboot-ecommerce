package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.dto.*;
import com.mehrdadmoradli.springboot_ecommerce.entity.User;
import com.mehrdadmoradli.springboot_ecommerce.entity.Address;
import com.mehrdadmoradli.springboot_ecommerce.entity.Cart;
import com.mehrdadmoradli.springboot_ecommerce.repository.*;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ModelMapper mapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(UserRegistrationDto userDto) {	
		User user = mapper.map(userDto, User.class);
		user.setRoles(Set.of("ROLE_USER"));
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Address address = new Address(userDto.getCountry(), userDto.getCity(), userDto.getStreet(), userDto.getPostalCode());
		user.addAddress(address);
		Cart cart = new Cart();
		cart.setUser(user);
		return repository.save(user);
	}
	
	@Override
	public User registerAdmin(UserRegistrationDto userDto) {	
		User user = mapper.map(userDto, User.class);
		user.setRoles(Set.of("ROLE_ADMIN"));
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Address address = new Address(userDto.getCountry(), userDto.getCity(), userDto.getStreet(), userDto.getPostalCode());
		user.addAddress(address);
		return repository.save(user);
	}
	
	@Override
	public User updateUser(UserUpdateDto userDto, String username) {
		User user = repository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		mapper.getConfiguration().setSkipNullEnabled(true);
		mapper.map(userDto, user);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Address address = new Address(userDto.getCountry(), userDto.getCity(), userDto.getStreet(), userDto.getPostalCode());
		user.removeAddress(user.getAddresses().get(0));
		user.addAddress(address);
		return repository.save(user);
	}
	
	@Override
	public User addNewAddress(Address address, String username) {
		
		User user = repository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		user.addAddress(address);
		return repository.save(user);
	}
	
	@Override
	public User getUserById(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		return user;
	}
	
	@Override
	public List<User> getAllUsers(){
		List<User> userList = repository.findAll();
		return userList;
	}
	
	@Override
	public void deleteUser(String username) {
		if (!repository.existsByUsername(username)) {
			throw new RuntimeException("User not found");
		}
		repository.deleteByUsername(username);
	}
}
