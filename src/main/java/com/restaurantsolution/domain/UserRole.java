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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class UserRole implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "RoleId",nullable=false)
private Long Id;

public Long getId() {
	return Id;
}

public void setId(Long id) {
	Id = id;
}

private String roleName;

@JsonBackReference
@OneToMany( mappedBy = "userRole")
private Set<AppUserRole> appUserRoles;

public Set<AppUserRole> getAppUserRoles() {
	return appUserRoles;
}

public void setAppUserRoles(Set<AppUserRole> appUserRoles) {
	this.appUserRoles = appUserRoles;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}



	
	

}
