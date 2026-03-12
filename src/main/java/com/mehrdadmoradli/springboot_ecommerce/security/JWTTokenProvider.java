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
	

	private Long jwtExpiration;
	private Key key;
	
	public JWTTokenProvider(@Value("${jwt.secret}") String jwtSecret, 
							@Value("${jwt.expiration}") Long jwtExpiration) {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtExpiration = jwtExpiration;
	}
	
	
	public String tokenGenerator(String userName, List<String> roles) {
		
        Date date = new Date();
        Date expiryDate = new Date(date.getTime() + jwtExpiration);

        return Jwts.builder()
        		.setSubject(userName)
        		.claim("roles", roles)
        		.setIssuedAt(date)
        		.setExpiration(expiryDate)
        		.signWith(key)
        		.compact();
	}
	
	public String getUserNameFromJWT(String token) {
		
		Claims claims = Jwts.parserBuilder()
		        .setSigningKey(key)
		        .build()
		        .parseClaimsJws(token)
		        .getBody();
	
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		}
		catch (JwtException ex) {
			return false;
		}
	}
}

