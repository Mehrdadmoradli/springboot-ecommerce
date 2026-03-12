package com.mehrdadmoradli.springboot_ecommerce.controller;

import com.mehrdadmoradli.springboot_ecommerce.security.JWTTokenProvider;
import com.mehrdadmoradli.springboot_ecommerce.repository.UserRepository;
import com.mehrdadmoradli.springboot_ecommerce.entity.User;
import com.mehrdadmoradli.springboot_ecommerce.dto.LoginRequestDto;
import com.mehrdadmoradli.springboot_ecommerce.dto.LoginResponseDto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class AuthController {
	
	@Autowired
	private JWTTokenProvider tokenProvider;
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody LoginRequestDto loginDto) {
		User user = repository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
		if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}
		String token = tokenProvider.tokenGenerator(user.getUsername(), user.getRoles().stream().toList());
		LoginResponseDto responseDto = new LoginResponseDto(token);
		return responseDto;
	}

}
