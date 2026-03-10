package com.mehrdadmoradli.springboot_ecommerce.config;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {
	
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
	        	.requestMatchers("/h2-console/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .httpBasic(Customizer.withDefaults());

	    return http.build();
	}
}