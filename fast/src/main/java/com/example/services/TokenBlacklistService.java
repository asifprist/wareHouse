package com.example.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwtToken.JwtToken;

@Service
public class TokenBlacklistService {
	@Autowired
	private JwtToken jwttoken;
	
	private final Set<String> blacklistedTokens = new HashSet<>();

    public void addToBlacklist(String token) {
        // Add the token to the blacklist
    //	jwttoken.generateToken(token);
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        // Check if the token is in the blacklist
        return blacklistedTokens.contains(token);
    }

}
