package com.mehrdadmoradli.springboot_ecommerce.entity;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
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
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Address> addresses = new ArrayList<>();
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	private Set<String> roles;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Cart cart;
	
	public User() {
		
	}
	public User(String email, String phoneNumber, String username, String password, String firstName, String lastName, Address initalAddress, Set<String> roles) {
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses.add(initalAddress);
		this.roles = roles;
	}
	
	public Long getId() {
	    return id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email ) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String userName ) {
		this.username = userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password ) {
		this.password = password;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName ) {
		this.firstName = firstName;
	}	
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName ) {
		this.lastName = lastName;
	}
	
	public Set<String> getRoles(){
		return this.roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}	
	
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	public List<Address> getAddresses(){
		return this.addresses;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
		address.setUser(this);
	}
	
	public void removeAddress(Address address) {
		
		if(!this.addresses.isEmpty()) {
			this.addresses.remove(address);
			address.setUser(null);
		}
	}
}
