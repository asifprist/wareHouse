package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.UserDetail;
import com.example.entity.UserFCD;
import com.example.repository.UserFCDrepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService{
	
	  @Autowired
	    private UserFCDrepository repository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<UserFCD> userInfo = repository.findByusername(username);
	        return userInfo.map(UserDetail::new)
	                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

	    }

}
