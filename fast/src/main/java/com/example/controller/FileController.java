package com.example.controller;

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

import com.example.entity.FileDocument;
import com.example.responseHandler.ResponseHandlerFCD;
import com.example.responseHandler.ResponseHandlerGetFCD;
import com.example.services.FileService;

import io.jsonwebtoken.io.IOException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	    @ApiOperation(value = "POST", notes = "API for file upload")
		@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
	        try {
	          FileDocument fil=  fileService.uploadFile(file);
	           return ResponseHandlerFCD.generateResponse("File uploaded successfully", HttpStatus.OK, "Correct", fil);
	            
	        } catch (IOException e) {
	          return ResponseHandlerFCD.generateResponse("Failed to upload file", HttpStatus.BAD_REQUEST, "null", null);
	       
	        }
	        catch(Exception e) {
	        	return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.OK, "null",  null);
	        }
	    }
		@RequestMapping(value = "/getAllFile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getAllfile() {

			try {
				List<FileDocument> all = fileService.getAll();

				return ResponseHandlerGetFCD.generateResponse("All file detail  ..", HttpStatus.OK, "Correct", all);
			}
			catch (IOException e) {
		          return ResponseHandlerGetFCD.generateResponse("Failed to upload file", HttpStatus.BAD_REQUEST, "null", null);
		       
		        }
			catch (Exception e) {

				return ResponseHandlerGetFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
			}

		}
		
		@RequestMapping(value = "/updateFile/{fileId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> updateFile(@PathVariable String fileId, @RequestParam("file") MultipartFile file) {
		    try {
		        FileDocument existingFile = fileService.getFileById(fileId);
		        
		        if (existingFile != null) {
		            // Update the file data
		            existingFile.setFileName(file.getOriginalFilename());
		            existingFile.setFileData(file.getBytes());
		            
		            // Save the updated file
		            FileDocument updatedFile = fileService.updateFile(existingFile);
		            
		            return ResponseHandlerFCD.generateResponse("File updated successfully", HttpStatus.OK, "Correct", updatedFile);
		        } else {
		            return ResponseHandlerFCD.generateResponse("File not found", HttpStatus.NOT_FOUND, "null", null);
		        }
		    } catch (IOException e) {
		        return ResponseHandlerFCD.generateResponse("Failed to update file", HttpStatus.BAD_REQUEST, "null", null);
		    } catch (Exception e) {
		        return ResponseHandlerFCD.generateResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR, "null", null);
		    }
		}

		
		@RequestMapping(value = "/getByFileId/{Id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getfileId(@PathVariable("Id") String clientId) {
			try {
				
			Optional<FileDocument> byId = fileService.getById(clientId);
			return ResponseHandlerFCD.generateResponse("File Id details  ..", HttpStatus.OK, "Correct", byId);
		}
		
			catch (Exception e) {
		
			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
		}

		@RequestMapping(value = "/getByFileName/{Id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getfileName(@PathVariable("Id") String clientId) {
			try {
				
			FileDocument byfileName = fileService.getByfileName(clientId);
			return ResponseHandlerFCD.generateResponse("File name details  ..", HttpStatus.OK, "Correct", byfileName);
		}
		
			catch (Exception e) {
		
			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
		}
		
		@RequestMapping(value = "/deleteFileId/{fileDel}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> deleteFileId(@PathVariable("fileDel") String fileDelId) {
			try {
				 FileDocument deleteById = fileService.deleteById(fileDelId);
				return ResponseHandlerFCD.generateResponse("File Id deleted Successfully", HttpStatus.OK, "Correct", deleteById);
			}catch(Exception e) {
				return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
			}
			
		}
		@RequestMapping(value="/deleteFileName/{filedele}",method=RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> deleteByname(@PathVariable("filedele") String fildel ){
			try {
			FileDocument deleteByfileName = fileService.deleteByfileName(fildel);
			return ResponseHandlerFCD.generateResponse("file name Document deleted successfully", HttpStatus.OK, "Correct", deleteByfileName);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
		}
}