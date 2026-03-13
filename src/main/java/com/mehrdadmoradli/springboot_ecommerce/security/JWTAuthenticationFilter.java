package com.mehrdadmoradli.springboot_ecommerce.security;

import com.mehrdadmoradli.springboot_ecommerce.repository.UserRepository;
import com.mehrdadmoradli.springboot_ecommerce.security.JWTTokenProvider;
import com.mehrdadmoradli.springboot_ecommerce.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private JWTTokenProvider tokenProvider;
	
	
	
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			if (tokenProvider.validateToken(token)) {
				String username = tokenProvider.getUserNameFromJWT(token);
				Optional<User> userOpt = repository.findByUsername(username);
				if (userOpt.get() != null) {
					User user = userOpt.get();
					var authorities = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken auth = 
                            new UsernamePasswordAuthenticationToken(user, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}
