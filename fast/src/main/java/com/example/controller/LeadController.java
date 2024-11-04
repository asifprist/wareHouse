package com.example.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Leads;
import com.example.responseHandler.ResponseHandlerFCD;
import com.example.responseHandler.ResponseHandlerGetFCD;
import com.example.services.LeadService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	private LeadService leadsService;

	
	 @ApiOperation(value = "POST", notes = "API for file upload")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> uploadLeadsFile(@RequestParam("file") MultipartFile file) {
		try {
			List<Leads> saveLeadsFromExcel = leadsService.saveLeadsFromExcel(file);
			return ResponseHandlerGetFCD.generateResponse("File Upload successfully ", HttpStatus.OK, "Correct",
					saveLeadsFromExcel);

		} catch (IOException e) {
			return ResponseHandlerGetFCD.generateResponse("Error uploading the file", HttpStatus.BAD_REQUEST, "null",
					null);

		} catch (Exception e) {
			return ResponseHandlerGetFCD.generateResponse("Something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllLeads() {
		try {
			List<Leads> leadsList = leadsService.getAllLeads();
			return ResponseHandlerGetFCD.generateResponse("All Client leads", HttpStatus.OK, "Correct", leadsList);

		} catch (Exception e) {
			return ResponseHandlerGetFCD.generateResponse("Something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
	
	}

	@RequestMapping(value="/update",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateLeads(@RequestParam("file") MultipartFile file){
		try {
			List<Leads> updateLeadsFromExcel = leadsService.saveLeadsFromExcel(file);
			return ResponseHandlerFCD.generateResponse("Leads updated", HttpStatus.OK, "Correct	", updateLeadsFromExcel);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("Something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
		
	}
	@RequestMapping(value = "/getByFileId/{Id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getfileId(@PathVariable("Id") String fileId) {
		try {
			Optional<Leads> byId = leadsService.getById(fileId);
		
		return ResponseHandlerFCD.generateResponse("File Id details  ..", HttpStatus.OK, "Correct", byId);
	}
	
		catch (Exception e) {
	
		return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
	}
	}
	@RequestMapping(value = "/deleteFileId/{fileDel}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteFileId(@PathVariable("fileDel") String fileDelId) {
		try {
			Leads deleteById = leadsService.deleteById(fileDelId);
			return ResponseHandlerFCD.generateResponse("File Id deleted Successfully", HttpStatus.OK, "Correct", deleteById);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
		
	}
}
//	    @PostMapping("/upload")
//	    public ResponseEntity<Object> uploadLeadsFile(@RequestParam("file") MultipartFile file) {
//	        try {
//	            leadsService.saveLeadsFromExcel(file);
//	          
//	            return ResponseEntity.ok("File uploaded successfully!");
//	        } catch (IOException e) {
//
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file");
//	        }
//	       
//	    }
	
