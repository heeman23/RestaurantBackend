package com.restaurantsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantsolution.domain.AppUser;



@Transactional
public interface ApplicationRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String loginId);

	AppUser findByContact(String cli);
	
	@Query("SELECT u FROM AppUser u left outer join fetch u.appUserRoles aur left outer join fetch aur.userRole ur  WHERE u.username =:username and u.password =:password and ur.roleName = 'admin'")
	AppUser findAdminUser(String username,String password);
	
//	AppUser findBy(String username,String password);
}
