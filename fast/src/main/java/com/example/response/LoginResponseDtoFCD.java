package com.example.response;

import lombok.Data;

@Data
public class LoginResponseDtoFCD {
	
	private String username;
	private String password;
	//@Indexed(unique = true)
	private String email;
	private String firstname;
	private String lastname;
	private String city;
	private String state;
	private String contact;
	private String address;
	
}
