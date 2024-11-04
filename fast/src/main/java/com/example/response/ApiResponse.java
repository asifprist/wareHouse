package com.example.response;

import java.util.List;

import lombok.Data;

@Data
public class ApiResponse {

	private ResponseCode responseCode;
	
	private List<ResponseData> responseData;
	
	

}
