package com.mehrdadmoradli.springboot_ecommerce.security;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Date;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
@Component
public class JWTTokenProvider {
	
	private final String JWT_SECRET = "+il5ItsHZbSy7C9AI6yfz0DviMGz+pxJlIyXoQzOiPg=";
	private final Long JWT_EXPIRATION = 3600000L;
	
	public String tokenGenerator(String userName, List<String> roles) {
		
        Date date = new Date();
        Date expiryDate = new Date(date.getTime() + JWT_EXPIRATION);
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
        
        return Jwts.builder()
        		.setSubject(userName)
        		.claim("roles", roles)
        		.setIssuedAt(date)
        		.setExpiration(expiryDate)
        		.signWith(key)
        		.compact();
	}
	
}
