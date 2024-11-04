package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.UserDetail;
import com.example.repository.UserFCDrepository;

@Service
public class UserRegistrationDetailsServiceFCD implements UserDetailsService {

	
	@Autowired
	private UserFCDrepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return userRepo.findByEmail(email).map(UserDetail::new).orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}
	
	

}
