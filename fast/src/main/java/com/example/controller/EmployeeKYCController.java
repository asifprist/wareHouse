package com.example.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.EmployeeKYC;
import com.example.responseHandler.ResponseHandlerFCD;
import com.example.responseHandler.ResponseHandlerGetFCD;
import com.example.services.EmployeeKYCService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/kyc")
public class EmployeeKYCController {

	@Autowired
	private EmployeeKYCService empservice;

	@RequestMapping(value = "/saveEmpKYC", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveEmpKyc(@Valid @RequestPart("aadharFile") MultipartFile aadharFile,
			@RequestPart("panFile") MultipartFile panFile, @RequestPart("passbookFile") MultipartFile passbookFile,
			@Valid @ModelAttribute EmployeeKYC empkyc) {
		try {
			EmployeeKYC saves = empservice.saveEmpKyc(empkyc, aadharFile, panFile, passbookFile);
			return ResponseHandlerFCD.generateResponse("Emp KYC detail added Successfully..", HttpStatus.OK, "Correct",
					saves);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
	}
	@RequestMapping(value = "/updateEmpKYC", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> updateKYC(@Valid @RequestPart("aadharFile") MultipartFile aadharFile,
			@RequestPart("panFile") MultipartFile panFile, @RequestPart("passbookFile") MultipartFile passbookFile,
			@Valid @ModelAttribute EmployeeKYC empkyc) {
	    try {
	    	EmployeeKYC upEmpKyc = empservice.saveEmpKyc(empkyc, aadharFile, panFile, passbookFile);
	       // EmployeeKYC updatedKYC = empservice.updateEmployeeKYC(empkyc, aadharFile, panFile, passbookFile);
	        return ResponseHandlerFCD.generateResponse("Employee Kyc detail updated", HttpStatus.OK, "null", upEmpKyc);
	    } catch (Exception e) {
	        return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR, "null", null);
	    }
	}
//----------------------------
	@RequestMapping(value = "/getAllEmpKYC", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllEmpKYC() {
		try {
			List<EmployeeKYC> all = empservice.getAll();

			return ResponseHandlerGetFCD.generateResponse("Employee KYC details  ..", HttpStatus.OK, "Correct", all);
		} catch (Exception e) {

			return ResponseHandlerGetFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null",
					null);
		}
	}
//	@RequestMapping(value = "/updateEmpKYC", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> updateKYC(@RequestBody EmployeeKYC updatekyc) {
//	    try {
//	        EmployeeKYC updatedKYC = empservice.updateEmployeeKYC(updatekyc);
//	        return ResponseHandlerFCD.generateResponse("Employee Kyc detail updated", HttpStatus.OK, "null", updatedKYC);
//	    } catch (Exception e) {
//	        return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR, "null", null);
//	    }
//	}

//	@RequestMapping(value = "/updateEmpKYC", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> updateKYC(@RequestBody EmployeeKYC updatekyc) {
//		try {
//			EmployeeKYC updateKYC = empservice.updateEmployeeKYC(updatekyc);
//			return ResponseHandlerFCD.generateResponse("Employee Kyc detail updated", HttpStatus.OK, "null", updateKYC);
//		} catch (Exception e) {
//			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.OK, "null", null);
//		}
//	}

	@RequestMapping(value = "/getEmpKYCById/{clId}", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getEmpId(@PathVariable("clId") String empId) {
		try {
			EmployeeKYC byId = empservice.getById(empId);
			return ResponseHandlerFCD.generateResponse("Employee KYC details  ..", HttpStatus.OK, "Correct", byId);
		} catch (NumberFormatException e) {
			return ResponseHandlerFCD.generateResponse("Invalid Emp KYC ID format...", HttpStatus.BAD_REQUEST, "null",
					null);
		} catch (Exception e) {

			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
	}

	@RequestMapping(value = "/deleteByEmpId/{clDel}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteEmpId(@PathVariable("clDel") String kycId) {
		try {
			EmployeeKYC deleteByEmpId = empservice.deleteByEmpId(kycId);
			return ResponseHandlerFCD.generateResponse("Employee  Id deleted Successfully", HttpStatus.OK, "Correct",
					deleteByEmpId);
		} catch (Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
	}

//	@RequestMapping(value = "/downloadEmpKycExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	private ResponseEntity<InputStreamResource> download() throws IOException {
//		try {
//
//			String fileName = "empkyc.xlsx";
//			ByteArrayInputStream inputStream = empservice.getDataDownloaded();
//			InputStreamResource response = new InputStreamResource(inputStream);
//
//			ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
//					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response);
//			return ResponseHandlerFCDDownload.generateResponse("excel file downloaded", HttpStatus.OK, "Correct",
//					responseEntity);
//		} catch (Exception e) {
//			return ResponseHandlerFCDDownload.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null",
//					null);
//		}
//	}
	@RequestMapping(value = "/downloadEmpKycExcel", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<InputStreamResource> download() throws IOException {
		String fileName = "EmployeeKYC.xlsx";
		ByteArrayInputStream inputStream = empservice.getDataDownloaded();
		InputStreamResource response = new InputStreamResource(inputStream);

		ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response);
		return responseEntity;
	}

}
//@RequestMapping(value = "/saveEmpKYC", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//public ResponseEntity<Object> saveEmpKyc(@RequestBody EmployeeKYC empkyc,
//		@RequestPart(value = "aadharImageFile", required = false) MultipartFile aadharImageFile,
//		@RequestPart(value = "panImageFile", required = false) MultipartFile panImageFile,
//		@RequestPart(value = "passbookImageFile", required = false) MultipartFile passbookImageFile) {
//
//	try {
//		// Handle AadharImage file upload
//		if (aadharImageFile != null) {
//			AadharImage aadharImage = new AadharImage();
//			handleFileUpload(aadharImageFile, aadharImage);
//			empkyc.getAadharImage().add(aadharImage);
//		}
//
//		// Handle PanImage file upload
//		if (panImageFile != null) {
//			PanImage panImage = new PanImage();
//			handleFileUpload(panImageFile, panImage);
//			empkyc.getPanImage().add(panImage);
//		}
//
//		// Handle AccountPassbook file upload
//		if (passbookImageFile != null) {
//			AccountPassbook passbook = new AccountPassbook();
//			handleFileUpload(passbookImageFile, passbook);
//			empkyc.getPassbookImage().add(passbook);
//		}
//
//		EmployeeKYC savedEmployeeKYC = empservice.save(empkyc);
//
//		return ResponseHandlerFCD.generateResponse("Emp KYC detail added Successfully..", HttpStatus.OK, "Correct",
//				savedEmployeeKYC);
//	} catch (Exception e) {
//		return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//	}
//}
//
//private void handleFileUpload(MultipartFile passbookImageFile, AccountPassbook passbook) throws IOException {
//	byte[] fileBytes = passbookImageFile.getBytes();
//	passbook.setData(fileBytes);
//
//}
//
//private void handleFileUpload(MultipartFile panImageFile, PanImage panImage) throws IOException {
//	byte[] fileBytes = panImageFile.getBytes();
//	  panImage.setData(fileBytes);
//
//}
//
//private void handleFileUpload(MultipartFile file, AadharImage fileImage) throws IOException {
//    byte[] fileBytes = file.getBytes();
//
//    fileImage.setData(fileBytes);
//    
//}

//@RequestMapping(value = "/saveEmpKYC", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//public ResponseEntity<Object> saveEmpKyc(
//		
//		@RequestPart("empkyc") EmployeeKYC empkyc,
//		@RequestPart(value = "aadharImage", required = false) MultipartFile aadharImageFile,
//		@RequestPart(value = "panImage", required = false) MultipartFile panImageFile,
//		@RequestPart(value = "passbookImage", required = false) MultipartFile passbookImageFile) {
//
//	try {
//		// Handle AadharImage file upload
//		if (aadharImageFile != null) {
//			AadharImage aadharImage = new AadharImage();
////			aadharImage.setFileId(null);
////			aadharImage.setUrl(null);
//			empkyc.getAadharImage().add(aadharImage);
//		}
//
//		// Handle PanImage file upload
//		if (panImageFile != null) {
//			PanImage panImage = new PanImage();
////			panImage.setFileId(null);
////			panImage.setUrl(null);
//			empkyc.getPanImage().add(panImage);
//		}
//
//		// Handle AccountPassbook file upload
//		if (passbookImageFile != null) {
//			AccountPassbook passbook = new AccountPassbook();
////			passbook.setFileId(null);
////			passbook.setUrl(null);
//			
//			empkyc.getPassbookImage().add(passbook);
//		}
//
//		EmployeeKYC savedEmployeeKYC = empservice.save(empkyc);
//
//		return ResponseHandlerFCD.generateResponse("Emp KYC detail added Successfully..", HttpStatus.OK, "Correct",
//				savedEmployeeKYC);
//	} catch (Exception e) {
//		return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//	}
//}

//@RequestMapping(value = "/saveEmpKYC", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//public ResponseEntity<Object> saveEmpKyc(@RequestBody Map<String, Object> request) {
//    try {
//        EmployeeKYC empkyc = convertMapToEmployeeKYC(request);
//        EmployeeKYC saves = empservice.save(empkyc);
//
//        return ResponseHandlerFCD.generateResponse("Emp KYC detail added Successfully..", HttpStatus.OK, "Correct", saves);
//    } catch (Exception e) {
//        return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//    }
//}
//
//private EmployeeKYC convertMapToEmployeeKYC(Map<String, Object> request) {
//    ObjectMapper objectMapper = new ObjectMapper();
//    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//    AadharImage aadharImage = objectMapper.convertValue(request.get("aadharImage"), AadharImage.class);
//    PanImage panImage = objectMapper.convertValue(request.get("panImage"), PanImage.class);
//
//    request.put("aadharImage", aadharImage);
//    request.put("panImage", panImage);
//
//    return objectMapper.convertValue(request, EmployeeKYC.class);
//}
//@RequestMapping(value = "/saveEmpKYC", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//public ResponseEntity<Object> saveEmpKyc(@RequestBody EmployeeKYC request) {
//    try {
//        EmployeeKYC empkyc = convertRequestToEmployeeKYC(request);
//        EmployeeKYC saves = empservice.save(empkyc);
//
//        return ResponseHandlerFCD.generateResponse("Emp KYC detail added Successfully..", HttpStatus.OK, "Correct", saves);
//    } catch (Exception e) {
//        return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//    }
//}
//
//private EmployeeKYC convertRequestToEmployeeKYC(EmployeeKYC request) {
//    EmployeeKYC employeeKYC = new EmployeeKYC();
//    // Map fields
//    employeeKYC.setLocation(request.getLocation());
//    employeeKYC.setEmpNo(request.getEmpNo());
//    employeeKYC.setBioNo(request.getBioNo());
//    employeeKYC.setName(request.getName());
//    employeeKYC.setEmailId(request.getEmailId());
//    employeeKYC.setDesignation(request.getDesignation());
//    employeeKYC.setReporting(request.getReporting());
//    employeeKYC.setManagerName(request.getManagerName());
//    employeeKYC.setSalary(request.getSalary());
//    employeeKYC.setDateOfJoining(request.getDateOfJoining());
//    employeeKYC.setBankName(request.getBankName());
//    employeeKYC.setAccountNumber(request.getAccountNumber());
//    employeeKYC.setPassbookImage(request.getPassbookImage());
//    employeeKYC.setIfsc(request.getIfsc());
//    employeeKYC.setAadharNumber(request.getAadharNumber());
//    employeeKYC.setAadharImage(request.getAadharImage());
//    employeeKYC.setPanNumber(request.getPanNumber());
//    employeeKYC.setPanImage(request.getPanImage());
//    employeeKYC.setHusbandAndFathersName(request.getHusbandAndFathersName());
//    employeeKYC.setCurrentAddress(request.getCurrentAddress());
//    employeeKYC.setPermanentAddress(request.getPermanentAddress());
//    employeeKYC.setRelation(request.getRelation());
//    employeeKYC.setRelationNumber(request.getRelationNumber());
//    employeeKYC.setDateOfBirth(request.getDateOfBirth());
//    employeeKYC.setContactNumber(request.getContactNumber());
//    employeeKYC.setAlternateNumber(request.getAlternateNumber());
//    employeeKYC.setAddedBy(request.getAddedBy());
//  
//
//    // Set additional fields like addedBy, createdAt, updatedAt, etc.
//
//    return employeeKYC;
//}
