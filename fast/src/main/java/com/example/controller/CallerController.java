	package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.CallFeedback;
import com.example.responseHandler.ResponseHandlerFCD;
import com.example.responseHandler.ResponseHandlerGetFCD;
import com.example.services.CallerService;

@RestController
@RequestMapping("/call")
public class CallerController {

	@Autowired
	private CallerService callerService;

	@RequestMapping(value ="/saveCallerFeeback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveCaller(@RequestBody CallFeedback caller) {
		try {
			CallFeedback save = callerService.save(caller);

			return ResponseHandlerFCD.generateResponse("caller feedback added Successfully..", HttpStatus.OK, "Correct",
					save);
		}
		catch (Exception e) {

			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
	}

	
	@RequestMapping(value = "/getAllcallerFeedback", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllCaller() {

		try {
			List<CallFeedback> all = callerService.getAll();

			return ResponseHandlerGetFCD.generateResponse("caller detail  ..", HttpStatus.OK, "Correct", all);
		}
		catch (Exception e) {

			return ResponseHandlerGetFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}

	}
	
	
	@RequestMapping(value = "/updateCallerFeedback", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCaller(@RequestBody CallFeedback caller){
		try {
			CallFeedback updateCaller = callerService.save(caller);
			return ResponseHandlerFCD.generateResponse("caller feedback updated", HttpStatus.OK, "Correct", updateCaller);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
			
		}
	}
	
	
	@RequestMapping(value = "/getByCallId/{clId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCallId(@PathVariable("clId") String clientId) {
		try {
			
		CallFeedback callerId = callerService.getCallId(clientId);
		return ResponseHandlerFCD.generateResponse("caller Feedback details  ..", HttpStatus.OK, "Correct", callerId);
	}
	
		catch (Exception e) {
	
		return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
	}
	}
	
//	
//	@RequestMapping(value = "/getByCallerId/{clId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> getCallerId(@PathVariable("clId") String callerId) {
//		try {
//			
//		CallFeedback callerIds = callerService.getBycallerId(callerId);
//		return ResponseHandlerFCD.generateResponse("caller Feedback details  ..", HttpStatus.OK, "Correct", callerIds);
//	}
//	
//		catch (Exception e) {
//	
//		return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//	}
//	}
	
	
	@RequestMapping(value = "/deletByeCallId/{clDel}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteCallId(@PathVariable("clDel")String cldel) {
		try {
			 CallFeedback deleteCallId = callerService.deleteCallId(cldel);
			return ResponseHandlerFCD.generateResponse("Call Id deleted Successfully", HttpStatus.OK, "Correct", deleteCallId);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
		
	}
	

}
