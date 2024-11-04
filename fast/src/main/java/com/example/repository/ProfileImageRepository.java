package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ProfileImage;
@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, String>{
	
	//Optional<ProfileImage> findByUsername(String username);
	
	ProfileImage findByUsername(String username);

	//ProfileImage deleteById(String file);

	//UserFCD save(UserFCD existingUser);
	
	//ProfileImage save(String username);

}
