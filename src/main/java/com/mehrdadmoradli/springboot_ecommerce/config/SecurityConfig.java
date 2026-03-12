package com.mehrdadmoradli.springboot_ecommerce.config;

import com.mehrdadmoradli.springboot_ecommerce.security.JWTAuthenticationEntryPoint;
import com.mehrdadmoradli.springboot_ecommerce.security.JWTAuthenticationFilter;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;



@EnableMethodSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private JWTAuthenticationEntryPoint unauthorizedHandler;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
	        .authorizeHttpRequests(auth -> auth
	        		.requestMatchers("/login", "/user_registration").permitAll()
	        		.requestMatchers("/h2-console/**").permitAll()
	        		.anyRequest().authenticated())
	        .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler))
	        .httpBasic(Customizer.withDefaults());
	    
	    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}