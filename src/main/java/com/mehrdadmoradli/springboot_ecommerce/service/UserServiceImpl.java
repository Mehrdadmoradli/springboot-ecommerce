package com.mehrdadmoradli.springboot_ecommerce.service;

import com.mehrdadmoradli.springboot_ecommerce.dto.*;
import com.mehrdadmoradli.springboot_ecommerce.entity.User;
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
	ModelMapper mapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(UserRegistrationDto userDto) {	
		User userToBeSaved = mapper.map(userDto, User.class);
		userToBeSaved.setRoles(Set.of("ROLE_USER"));
		userToBeSaved.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser = repository.save(userToBeSaved);
		return savedUser;
	}
	@Override
	public User registerAdmin(UserRegistrationDto userDto) {	
		User userToBeSaved = mapper.map(userDto, User.class);
		userToBeSaved.setRoles(Set.of("ROLE_ADMIN"));
		userToBeSaved.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser = repository.save(userToBeSaved);
		return savedUser;
	}
	@Override
	public User updateUser(UserUpdateDto userDto, Long id) {
		User userToBeUpdated = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		mapper.getConfiguration().setSkipNullEnabled(true);
		mapper.map(userDto, userToBeUpdated);
		userToBeUpdated.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser = repository.save(userToBeUpdated);
		return savedUser;
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
	public void deleteUser(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("User not found!");
		}
		repository.deleteById(id);
	}
}
