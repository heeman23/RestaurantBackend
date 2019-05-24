package com.restaurantsolution.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class AppUserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long getAppUserRoleId() {
		return appUserRoleId;
	}

	public void setAppUserRoleId(Long appUserRoleId) {
		this.appUserRoleId = appUserRoleId;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	@JsonManagedReference
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AppUserRoleId",nullable=false)
	private Long appUserRoleId;
	
	
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "UserId_FK", referencedColumnName = "userId", nullable = false)
	private AppUser appUser;

	@JsonManagedReference
	@ManyToOne()
	@JoinColumn(name = "RoleId_FK", referencedColumnName = "RoleId", nullable = false)
	private UserRole userRole;

}
