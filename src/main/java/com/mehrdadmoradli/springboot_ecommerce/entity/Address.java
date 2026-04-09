package com.mehrdadmoradli.springboot_ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private String postalCode;
	


    public Address() {}


    public Address(String country, String city, String street, String postalCode) {
    	this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }
    
    public Long getId() {
    	return this.id;
    }

    public User getUSer() {
    	return this.user;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
    
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

	
