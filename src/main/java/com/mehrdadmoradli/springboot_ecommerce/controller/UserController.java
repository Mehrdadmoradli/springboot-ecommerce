package com.mehrdadmoradli.springboot_ecommerce.controller;

import com.mehrdadmoradli.springboot_ecommerce.service.*;
import com.mehrdadmoradli.springboot_ecommerce.entity.*;
import com.mehrdadmoradli.springboot_ecommerce.dto.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	ModelMapper mapper;
	
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRegistrationDto userDto){
		
			User savedUser = userService.registerUser(userDto);
			UserResponseDto responseDto = mapper.map(savedUser, UserResponseDto.class);
			return ResponseEntity.status(201).body(responseDto);
		}
	
	@PostMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserResponseDto> createAdmin(@Valid @RequestBody UserRegistrationDto userDto){
		
			User savedUser = userService.registerAdmin(userDto);
			UserResponseDto responseDto = mapper.map(savedUser, UserResponseDto.class);
			return ResponseEntity.status(201).body(responseDto);
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserUpdateDto userDto, @PathVariable Long id){
		User updatedUser = userService.updateUser(userDto, id);
		UserResponseDto responseDto = mapper.map(updatedUser, UserResponseDto.class);
		return ResponseEntity.ok(responseDto);
	}
	

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
		User user = userService.getUserById(id);
		UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		List<User> userList = userService.getAllUsers();
		List<UserResponseDto> userListDto = new ArrayList<>();
		for(User u : userList) {
			UserResponseDto userDto = mapper.map(u, UserResponseDto.class);
			userListDto.add(userDto);
		}
		return ResponseEntity.ok(userListDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.ok("User has been removed");
	}	
}	






