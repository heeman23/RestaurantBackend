package com.restaurantsolution.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantsolution.domain.AppUser;
import com.restaurantsolution.repository.ApplicationRepository;
import com.restaurantsolution.service.UserService;


@Service
public class UserServiceImp implements UserService {
	
	
@Autowired	
ApplicationRepository applicationRepository;

@Override
public AppUser getUserByContact(String cli) {
	return applicationRepository.findByContact(cli);
}

@Override
public AppUser getAdminUserByUserName(String username, String password) {
	return applicationRepository.findAdminUser(username, password);
}

}
