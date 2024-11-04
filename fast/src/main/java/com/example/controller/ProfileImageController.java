package com.example.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.ProfileImage;
import com.example.repository.ProfileImageRepository;
import com.example.responseHandler.ResponseHandlerFCD;
import com.example.responseHandler.ResponseHandlerGetFCD;

@RestController
@RequestMapping("/profileimage")
public class ProfileImageController {

	@Autowired
	private ProfileImageRepository profileImgRepo;

//	@Autowired
//	private UserFCDrepository profileImgRepos;

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("username") String username) {

		try {
			ProfileImage existingProfile = profileImgRepo.findByUsername(username);

			if (existingProfile == null) {
				existingProfile = new ProfileImage();
				existingProfile.setUsername(username);
			}

			existingProfile.setImagePath(file.getBytes());

			ProfileImage save = profileImgRepo.save(existingProfile);
			return ResponseHandlerFCD.generateResponse("Profile Image uploaded successfully!", HttpStatus.OK, "Correct", save);
		} catch (IOException e) {
            return ResponseHandlerFCD.generateResponse("Failed to upload file!", HttpStatus.OK, "null", null);
			
		}
		catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, username, null);
		}
	}
	@RequestMapping(value = "/updateImage", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> handleFileUpdate(@RequestParam("file") MultipartFile file,
			@RequestParam("username") String username) {

		try {
			ProfileImage existingProfile = profileImgRepo.findByUsername(username);

			if (existingProfile == null) {
				existingProfile = new ProfileImage();
				existingProfile.setUsername(username);
			}

			existingProfile.setImagePath(file.getBytes());
			
			 ProfileImage save = profileImgRepo.save(existingProfile);
			return ResponseHandlerFCD.generateResponse("Profile Image updated successfully!", HttpStatus.OK, "Correct", save);
		} 
		catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, username, null);
		}
	}
	
//	@RequestMapping(value = "/deleteprofileImage{filedele}",method = RequestMethod.DELETE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> deleteProfileImage(@PathVariable("filedele") String file){
//		try {
//			//ProfileImage delete = profileImgRepo.deleteById(file);
//			return ResponseHandlerFCD.generateResponse("Profile Image deleted successfully", HttpStatus.OK, "Correct", delete);
//		}catch(Exception e) {
//			return ResponseHandlerFCD.generateResponse("Something went wrong", HttpStatus.BAD_REQUEST, "null", null);
//			
//		}
//	}
//	@SuppressWarnings("unused")
//	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
//			@RequestParam("username") String username) {
//		try {
//			UserFCD existingUser = profileImgRepo.findByUsername(username);
//
//			if (existingUser == null) {
//				existingUser = new UserFCD();
//				existingUser.setUsername(username);
//			}
//
//			ProfileImage existingProfile = existingUser.getProfileImage();
//
//			if (existingProfile == null) {
//				existingProfile = new ProfileImage();
//				existingProfile.setUsername(username);
//			}
//
//			existingProfile.setImagePath(file.getBytes());
//			existingUser.setProfileImage(existingProfile);
//
//			UserFCD savedUser = profileImgRepo.save(existingUser);
//
//			return ResponseHandlerFCD.generateResponse("ProfileImage uploaded successfully!", HttpStatus.OK, "Correct",
//					savedUser);
//		} catch (IOException e) {
//			return ResponseHandlerFCD.generateResponse("Failed to upload file!", HttpStatus.BAD_REQUEST, "null", null);
//		} catch (Exception e) {
//			return ResponseHandlerFCD.generateResponse("Something went wrong", HttpStatus.BAD_REQUEST, username, null);
//		}
//	}
//	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
//			@RequestParam("username") String username) {
//
//		try {
//		 UserFCD byUsername = profileImgRepos.findByUsername(username);
//ProfileImage existingProfile=new ProfileImage();
//			if (existingProfile == null) {
//				existingProfile = new ProfileImage();
//				existingProfile.setUsername(username);
//			}
//
//			existingProfile.setImagePath(file.getBytes());
//
//			 UserFCD save = profileImgRepos.save(existingProfile);
//			return ResponseHandlerFCD.generateResponse("ProfileImage uploaded successfully!", HttpStatus.OK, "Correct", save);
//		} catch (IOException e) {
//            return ResponseHandlerFCD.generateResponse("Failed to upload file!", HttpStatus.OK, "null", null);
//			
//		}
//		catch(Exception e) {
//			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, username, null);
//		}
//	}
//	

//	@RequestMapping(value = "/updateImage", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> handleFileUpdate(@RequestParam("file") MultipartFile file,
//			@RequestParam("username") String username) {
//
//		try {
//			ProfileImage existingProfile = profileImgRepo.findByUsername(username);
//
//			if (existingProfile == null) {
//				existingProfile = new ProfileImage();
//				existingProfile.setUsername(username);
//			}
//
//			existingProfile.setImagePath(file.getBytes());
//
//			ProfileImage save = profileImgRepo.save(existingProfile);
//			return ResponseHandlerFCD.generateResponse("ProfileImage updated successfully!", HttpStatus.OK, "Correct", save);
//		} catch (IOException e) {
//            return ResponseHandlerFCD.generateResponse("Failed to update file!", HttpStatus.OK, "null", null);
//			
//		}
//		catch(Exception e) {
//			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, username, null);
//		}
//	}

//	@RequestMapping(value = "/getfile", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> getFile(@PathVariable String username) {
//		
//		try {
//			ProfileImage profile = profileImgRepo.findByUsername(username);
//			return ResponseHandlerGetFCD.generateResponse("ProfileImage showing", HttpStatus.OK, "Correct", profile);
//		} catch (Exception e) {
//			return ResponseHandlerGetFCD.generateResponse("Something went wrong", HttpStatus.BAD_REQUEST, "null", null);
//		}
//
//	}
	@RequestMapping(value = "/getfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getFile() {

		try {
			List<ProfileImage> all = profileImgRepo.findAll();
			return ResponseHandlerGetFCD.generateResponse("ProfileImage showing", HttpStatus.OK, "Correct", all);
		} catch (Exception e) {
			return ResponseHandlerGetFCD.generateResponse("Something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}

	}
}
