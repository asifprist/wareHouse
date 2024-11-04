package com.example.entity;

import org.springframework.stereotype.Indexed;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegistrationRequestFCD(     
		@NotEmpty
		String firstname,
		@NotEmpty
        String lastname,
        @NotEmpty
        String username,
       // @Indexed(unique = true)
        @Email(message = "Please provide a valid email address")
        String email,
        
       // long mobileNumber,
        String contact,
     
        //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters long and include at least one letter and one number.")
        String password,
        
        @NotEmpty
		String city,
		@NotEmpty
		String state,
		//String token,
		@NotEmpty
		String address,
		@NotEmpty
		String gender,
		String roles,
		String otp,
       
        String code,
	    String message,
	    String description,
	    String complainCode)  {

}
