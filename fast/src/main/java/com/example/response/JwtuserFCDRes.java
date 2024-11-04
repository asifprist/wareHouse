package com.example.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtuserFCDRes {
	
	private String token;
	private String username;
	private Set<String> roles;

}
