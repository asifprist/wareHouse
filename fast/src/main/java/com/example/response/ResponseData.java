package com.example.response;

import org.springframework.stereotype.Indexed;

import lombok.Data;

@Data
public class ResponseData {
	private boolean success;
	private String complainCode;
	private String username;
	//@Indexed(unique = true)
	private String email;
	private String state;
	private String token;
}
