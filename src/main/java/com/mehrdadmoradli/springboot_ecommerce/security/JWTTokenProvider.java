package com.mehrdadmoradli.springboot_ecommerce.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Date;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
@Component
public class JWTTokenProvider {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	@Value("${jwt.expiration}")
	private Long jwtExpiration;
	
	private final Key KEY = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	
	public String tokenGenerator(String userName, List<String> roles) {
		
        Date date = new Date();
        Date expiryDate = new Date(date.getTime() + jwtExpiration);

        return Jwts.builder()
        		.setSubject(userName)
        		.claim("roles", roles)
        		.setIssuedAt(date)
        		.setExpiration(expiryDate)
        		.signWith(KEY)
        		.compact();
	}
	
	public String getUserNameFromJWT(String token) {
		
		Claims claims = Jwts.parserBuilder()
		        .setSigningKey(KEY)
		        .build()
		        .parseClaimsJws(token)
		        .getBody();
	
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parserBuilder()
			.setSigningKey(KEY)
			.build()
			.parseClaimsJws(token);
			return true;
		}
		catch (JwtException ex) {
			return false;
		}
	}
}

