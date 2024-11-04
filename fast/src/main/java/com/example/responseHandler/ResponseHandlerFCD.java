package com.example.responseHandler;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandlerFCD {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, String error,
			Object responseObj) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("responseCode", Map.of("code", status.value(), "message", message, "description", error));
		map.put("responseData", Collections.singletonList(responseObj));

		return new ResponseEntity<>(map, status);
	}

	public static void main(String[] args) {
		
	//	String message = "User logged in successfully";
		String message="";
		HttpStatus status = HttpStatus.OK;
		String error = "correct";
		 

		//Map<String, Object> responseData = Map.of("success", true, "userName", "", "email", "", "state", ""
Map<String,Object> responseData=Map.of("success",true,"", "", "", "", "", ""
		);

		ResponseEntity<Object> responseEntity = generateResponse(message, status, error, responseData);

		System.out.println(responseEntity.getBody());
	}

}
