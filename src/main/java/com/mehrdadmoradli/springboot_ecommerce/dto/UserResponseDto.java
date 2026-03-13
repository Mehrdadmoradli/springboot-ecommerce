package com.mehrdadmoradli.springboot_ecommerce.dto;
import java.util.Set;

public class UserResponseDto {
	
    private String username;
    private String email;
    private Set<String> roles;
    
    public UserResponseDto() {
    	
    }
    public UserResponseDto(String username, String email, Set<String> roles) {
    	this.username = username;
    	this.email = email;
    	this.roles = roles;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Set<String> getRoles(){
    	return this.roles;
    }
    public void setRoles(Set<String> roles) {
    	this.roles = roles;
    }
    
}
