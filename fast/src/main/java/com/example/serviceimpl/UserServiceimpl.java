package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.RegistrationRequestFCD;
import com.example.entity.UserFCD;
import com.example.requestdto.LoginRequestDtoFCD;
import com.example.response.ApiResponse;
import com.example.response.RegistrationResponseDtoFCD;

@Service
public interface UserServiceimpl {

	List<UserFCD> getUsers();

	RegistrationResponseDtoFCD registerUser(RegistrationRequestFCD request);

	Optional<UserFCD> findByEmail(String email);

	//ApiResponse login(LoginRequestDtoFCD request);

	
	UserFCD findByusername(String username);

	//UserDetails loadUserByUsername(String username) throws UserAlreadyExistsExceptionFCD;

	//ApiResponse login(LoginRequestDtoFCD request, String jwt);

	//ApiResponse login(LoginRequestDtoFCD request);

	//Optional<UserFCD> findByEmail(String email);

	ApiResponse login(LoginRequestDtoFCD request);

	//LoginResponseDtoFCD login(LoginRequestDtoFCD request);

	
}
