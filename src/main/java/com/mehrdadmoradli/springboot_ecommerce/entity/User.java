package com.mehrdadmoradli.springboot_ecommerce.entity;
import java.util.Set;
import java.time.*;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	private String country;
	
	private String city;
	
	private String street;
	
	@Column(nullable = false)
	private String postalCode;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	private Set<String> roles;
	
	public User() {
		
	}
	public User(String email, String phoneNumber, String username, String password, String firstName, String lastName, String country, String city, String street, String postalCode, Set<String> roles) {
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
		this.roles = roles;
	}
	
	public Long getId() {
	    return id;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getCountry() {
		return this.country;
	}
	public String getCity() {
		return this.city;
	}
	public String getStreet() {
		return this.street;
	}
	public String getPostalCode() {
		return this.postalCode;
	}
	public Set<String> getRoles(){
		return this.roles;
	}
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	public void setEmail(String email ) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setUsername(String userName ) {
		this.username = userName;
	}
	public void setPassword(String password ) {
		this.password = password;
	}
	public void setFirstName(String firstName ) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName ) {
		this.lastName = lastName;
	}
	public void setCountry(String country ) {
		this.country = country;
	}
	public void setCity(String city ) {
		this.city = city;
	}
	public void setStreet(String street ) {
		this.street = street;
	}
	public void setPostalCode(String postalCode ) {
		this.postalCode = postalCode;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}
	
}
