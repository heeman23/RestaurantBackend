package com.restaurantsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import com.restaurantsolution.domain.UserRole;

@Transactional
public interface AppRoleRepository extends JpaRepository<UserRole, Long> {

	UserRole findByroleName(String roleName);
	
	
}
