package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ProfileImage;
import com.example.entity.UserFCD;

@Repository
public interface UserFCDrepository extends JpaRepository<UserFCD, String> {

	Optional<UserFCD> findByEmail(String email);

	Optional<UserFCD> findByusername(String username);
	
	Optional<UserFCD> findByotp(String otp);

	UserFCD findByUsername(String username);

	UserFCD save(ProfileImage existingProfile);

	//UserFCD findByUsername(String username);

}
