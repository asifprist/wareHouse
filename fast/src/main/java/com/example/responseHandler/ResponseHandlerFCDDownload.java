package com.example.responseHandler;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandlerFCDDownload {
	

	public static ResponseEntity<InputStreamResource> generateResponse(String message, HttpStatus status, String error,
			Object responseObj) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("responseCode", Map.of("code", status.value(), "message", message, "description", error));
		map.put("responseData", Collections.singletonList(responseObj));

		return new ResponseEntity<InputStreamResource>(status);
	}

	public static void main(String[] args) {
		
	
		String message="";
		HttpStatus status = HttpStatus.OK;
		String error = "correct";
		 

		
Map<String,Object> responseData=Map.of("success",true,"", "", "", "", "", ""
		);

		ResponseEntity<InputStreamResource> responseEntity = generateResponse(message, status, error, responseData);

		System.out.println(responseEntity.getBody());
	}


}
