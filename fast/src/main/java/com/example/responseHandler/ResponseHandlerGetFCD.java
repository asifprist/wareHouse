package com.example.responseHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandlerGetFCD {
	
	 public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, String error, Object responseObj) {
	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("responseCode", Map.of(
	                "code", status.value(),
	                "message", message,
	                "description", error
	        ));
	        map.put("responseData", responseObj);

	        return new ResponseEntity<>(map, status);
	    }

	    public static void main(String[] args) {
	        // Example usage
	        String message = "";
	        HttpStatus status = HttpStatus.OK;
	        String error = "correct";

	        Map<String, Object> responseData = Map.of(
	                "success", true,
	                "complainCode", "200",
	                "userName", "",
	                "email", "",
	                "state", ""
	               
	      
	        		);

	        ResponseEntity<Object> responseEntity = generateResponse(message, status, error, responseData);

	        // Print the response
	        System.out.println(responseEntity.getBody());
	    }

}
