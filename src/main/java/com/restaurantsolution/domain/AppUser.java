package com.restaurantsolution.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.snug.dialx.domain.AppUserRole;

@Entity
public class AppUser implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId",nullable=false)
	private Long Id;
	
	private String FullName;
	
	private String username;
	
	private String contact;
	private String password;
	
	
	@JsonManagedReference
	@OneToMany( mappedBy = "appUser")
	private Set<AppUserRole> appUserRoles;
	
	@JsonBackReference
	@OneToMany( mappedBy = "appUser")
	private Set<OrderDetails> orderDetails;
	
	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public Set<AppUserRole> getAppUserRoles() {
		return appUserRoles;
	}
	public void setAppUserRoles(Set<AppUserRole> appUserRoles) {
		this.appUserRoles = appUserRoles;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	

}
