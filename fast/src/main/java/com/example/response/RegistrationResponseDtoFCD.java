package com.example.response;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Component
@Data
@Setter
@Getter
public class RegistrationResponseDtoFCD {
	
	private String firstname;
	private String lastname;
	private String username;
	//@Indexed(unique = true)
	private String email;
	private String contact;
	private String address;
    private String roles;
	//private String gender;
	//private String token;

}
