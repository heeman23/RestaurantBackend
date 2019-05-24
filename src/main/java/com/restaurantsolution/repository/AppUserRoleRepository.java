package com.restaurantsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import com.restaurantsolution.domain.AppUserRole;

@Transactional
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long> {

	
	
}
