package com.restaurantsolution.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restaurantsolution.domain.AppUser;

@Component
public interface UserService {

	
	
public AppUser getUserByContact(String cli); 	

public AppUser getAdminUserByUserName(String username,String password); 
	
}
