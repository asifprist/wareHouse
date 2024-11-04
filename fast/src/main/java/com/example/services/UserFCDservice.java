package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.RegistrationRequestFCD;
import com.example.entity.UserFCD;
import com.example.exception.UserAlreadyExistsExceptionFCD;
import com.example.exception.UserNotFoundExceptionFCD;
import com.example.jwtToken.JwtToken;
import com.example.repository.UserFCDrepository;
import com.example.requestdto.LoginRequestDtoFCD;
import com.example.response.ApiResponse;
import com.example.response.RegistrationResponseDtoFCD;
import com.example.response.ResponseCode;
import com.example.response.ResponseData;
import com.example.serviceimpl.UserServiceimpl;
import com.example.util.EmailUtil;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserFCDservice implements UserServiceimpl {

	@Autowired
	private UserFCDrepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private JwtToken jwtToken;

	@Override
	public List<UserFCD> getUsers() {

		return userRepo.findAll();
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserFCD> optional = userRepo.findbyUserName(username);
//		if (!optional.isPresent()) {
//			throw new UsernameNotFoundException("User Not Present !");
//		} else {
//			UserFCD userInfo = optional.get();
//			Set<Role> roles = userInfo.getRoles();
//			Set<GrantedAuthority> authorities = new HashSet<>();
//			for (Role role : roles) {
//				authorities.add(new SimpleGrantedAuthority(role.getAdmin())); // Use proper method to get role name
//				authorities.add(new SimpleGrantedAuthority(role.getEmployee()));
//				authorities.add(new SimpleGrantedAuthority(role.getAdmin()));
//			}
//			return new org.springframework.security.core.userdetails.User(userInfo.getUserName(),
//					userInfo.getPassword(), // Assuming you have a getPassword method in UserFCD
//					authorities);
//		}
//	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		 Optional<UserFCD> optional = userRepo.findbyUserName(username);
//			if (!optional.isPresent()) {
//			throw new UsernameNotFoundException("User Not Present !");
//		}else {
//			UserFCD userInfo = optional.get();
//			Set<Role> roles = userInfo.getRoles();
//			Set<GrantedAuthority> auth = new HashSet<>();
//			for(Role role:roles) {
//				auth.add(new SimpleGrantedAuthority(role));				
//			}
//			return new UserFCD(username,userInfo.getEmail(),auth);
//		}
//	}
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//			Optional<UserInfo> optional = userRepo.findByUsername(username);
//			if (!optional.isPresent()) {
//				throw new UsernameNotFoundException("User Not Present !");
//			}else {
//				UserInfo userInfo = optional.get();
//				Set<String> roles = userInfo.getRoles();
//				Set<GrantedAuthority> auth = new HashSet<>();
//				for(String role:roles) {
//					auth.add(new SimpleGrantedAuthority(role));				
//				}
//				return new UserInfo(username,userInfo.getPass(),auth);
//			}
//		
//	}
//registration user
	@Override
	public RegistrationResponseDtoFCD registerUser(RegistrationRequestFCD request) {
		Optional<UserFCD> user = findByEmail(request.email());
		if (user.isPresent()) {
			throw new UserAlreadyExistsExceptionFCD("User with email " + request.email() + " already exists");
		}

		UserFCD newUser = new UserFCD();
		newUser.setEmail(request.email());
		newUser.setFirstname(request.firstname());
		newUser.setLastname(request.lastname());
		newUser.setPassword(passwordEncoder.encode(request.password()));
		newUser.setContact(request.contact());
		newUser.setCity(request.city());
		newUser.setRoles(request.roles());
		newUser.setState(request.state());
		newUser.setUsername(request.username());
		newUser.setAddress(request.address());
		newUser.setGender(request.gender());
		newUser.setOtp(request.otp());

		UserFCD savedUser = userRepo.save(newUser);

		// Create and return a response DTO based on the saved user
		RegistrationResponseDtoFCD response = new RegistrationResponseDtoFCD();
		response.setFirstname(savedUser.getFirstname());
		response.setLastname(savedUser.getLastname());
		response.setUsername(savedUser.getUsername());
		response.setEmail(savedUser.getEmail());
		response.setContact(savedUser.getContact());
		response.setAddress(savedUser.getAddress());
		response.setRoles(savedUser.getRoles());

		return response;
	}

//	@Override
//	public LoginResponseDtoFCD login(LoginRequestDtoFCD request) {
//
//		Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());
//
////		if (existingUser.isPresent()) {
////		    // Update the existing user instead of saving a new one
////		    UserFCD existing = existingUser.get();
//		UserFCD existing=new UserFCD();
//		    existing.setPassword(request.getPassword());
//		    existing.setEmail(request.getEmail());
//		    UserFCD save = userRepo.save(existing);
//		
//		    // Return response as needed
//		    String token = jwtToken.generateToken(existing.getUsername());
//		    LoginResponseDtoFCD logres = new LoginResponseDtoFCD();
//		    logres.setUsername(save.getUsername());
//		    logres.setEmail(save.getEmail());
//		    logres.setState(save.getState());
//		    logres.setSuccess(true);
//		    logres.setToken(token);
//		    logres.setComplainCode("200");
//
//		    return logres;
//		}
//	
//		} else {
//		    // User doesn't exist, proceed with saving the new user
//		    UserFCD logreq = new UserFCD();
//		    // set other fields...
//		    UserFCD save = userRepo.save(logreq);
//
//		    // Return response as needed
//		    String token = jwtToken.generateToken(save.getUsername());
//		    LoginResponseDtoFCD logres = new LoginResponseDtoFCD();
//		    logres.setUsername(save.getUsername());
//		    logres.setEmail(save.getEmail());
//		    logres.setState(save.getState());
//		    logres.setSuccess(true);
//		    logres.setToken(token);
//		    logres.setComplainCode("200");
//
//		    return logres;
//		}
	
//		Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());
//
//		if (existingUser.isPresent()) {
//			// Update the existing user instead of saving a new one
//			UserFCD existing = existingUser.get();
//			existing.setPassword(request.getPassword());
//			existing.setEmail(request.getEmail());
//			userRepo.save(existing);
//			// Return response as needed
//		} else {
//			// User doesn't exist, proceed with saving the new user
//			UserFCD logreq = new UserFCD();
//			// set other fields...
//			UserFCD save = userRepo.save(logreq);
//			// Return response as needed
//			String token = jwtToken.generateToken(logreq.getUsername());
//			LoginResponseDtoFCD logres = new LoginResponseDtoFCD();
//			logres.setUsername(save.getUsername());
//			logres.setEmail(save.getEmail());
//			logres.setState(save.getState());
//			logres.setSuccess(true);
//			logres.setToken(token);
//			logres.setComplainCode("200");
//
//		}
//		return null;
//
//	}

	@Override
	public Optional<UserFCD> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	// token
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserFCD> userInfo = userRepo.findByuserName(username);
//
//		return userInfo.map(UserDetail::new)
//				.orElseThrow(() -> new UserNotFoundExceptionFCD("user not found" + username));
//	}

	// login

	// Login service method
	@Override
	public ApiResponse login(LoginRequestDtoFCD request) {
		ApiResponse apiResponse = new ApiResponse();
		ResponseCode responseCode = new ResponseCode();

		try {
			Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());

			if (existingUser.isPresent()) {
				UserFCD user = existingUser.get();
				if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
					responseCode.setCode("401");
					responseCode.setMessage("Invalid credentials");
					responseCode.setDescription("yes");
					apiResponse.setResponseCode(responseCode);
				} else {
					String token = jwtToken.generateToken(user.getUsername());
					responseCode.setCode("200");
					responseCode.setMessage("User logged in successfully");
					responseCode.setDescription("correct");

					// Creating and populating ResponseData
					ResponseData responseData = new ResponseData();
					responseData.setUsername(user.getUsername());
					responseData.setEmail(user.getEmail());
					responseData.setState(user.getState());
					responseData.setSuccess(true);
					responseData.setToken(token);
					responseData.setComplainCode("200");

					List<ResponseData> responseDataList = new ArrayList<>();
					responseDataList.add(responseData);

					apiResponse.setResponseCode(responseCode);
					apiResponse.setResponseData(responseDataList);
				}
			} else {
				responseCode.setCode("404");
				responseCode.setMessage("User not found by this email or login");
				responseCode.setDescription("data not set");
				apiResponse.setResponseCode(responseCode);
			}
		} catch (Exception e) {
			responseCode.setCode("500");
			responseCode.setMessage("An error occurred while logging in");
			responseCode.setDescription("check internet connection");
			apiResponse.setResponseCode(responseCode);
		}

		return apiResponse;
	}

//	@Override
//	public ApiResponse login(LoginRequestDtoFCD request) {
//	    ApiResponse apiResponse = new ApiResponse();
//	    ResponseCode responseCode = new ResponseCode();
//
//	    try {
//	        Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());
//
//	        if (existingUser.isPresent()) {
//	            UserFCD user = existingUser.get();
//	            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//	                responseCode.setCode("401");
//	                responseCode.setMessage("Invalid credentials");
//	                apiResponse.setResponseCode(responseCode);
//	            } else {
//	                responseCode.setCode("200");
//	                responseCode.setMessage("User logged in successfully");
//	                // Set whatever data you need in ResponseData
//
//	                ResponseData responseData = new ResponseData();
//	                // Set whatever data you need in ResponseData
//
//	                List<ResponseData> responseDataList = new ArrayList<>();
//	                responseDataList.add(responseData);
//
//	                apiResponse.setResponseCode(responseCode);
//	                apiResponse.setResponseData(responseDataList);
//	            }
//	        } else {
//	            responseCode.setCode("404");
//	            responseCode.setMessage("User not found by this email or login");
//	            apiResponse.setResponseCode(responseCode);
//	        }
//	    } catch (Exception e) {
//	        responseCode.setCode("500");
//	        responseCode.setMessage("An error occurred while logging in");
//	        apiResponse.setResponseCode(responseCode);
//	    }
//
//	    return apiResponse;
//	}
	// 25 12 2023
//	@Override
//	public ApiResponse login(LoginRequestDtoFCD request, String jwt) {
//	    ApiResponse apiResponse = new ApiResponse();
//	    ResponseCode responseCode = new ResponseCode();
//
//	    try {
//	        Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());
//
//	        if (existingUser.isPresent()) {
//	            UserFCD user = existingUser.get();
//	            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//	                // Incorrect password
//	                return getUnauthorizedResponse("Invalid credentials", "Incorrect password");
//	            } else {
//	                // Successful login
//	                responseCode.setCode("200");
//	                responseCode.setMessage("User logged in successfully");
//	                responseCode.setDescription(null);
//
//	                ResponseData responseData = new ResponseData();
//	                responseData.setComplainCode(null);
//	                // Set whatever data you need in ResponseData
//
//	                List<ResponseData> responseDataList = new ArrayList<>();
//	                responseDataList.add(responseData);
//	                apiResponse.setResponseData(responseDataList);
//	            }
//	        } else {
//	            // User not found
//	            return getNotFoundResponse("User not found", "No user exists with this email");
//	        }
//	    } catch (Exception e) {
//	        // Internal server error
//	        return getInternalErrorResponse();
//	    }
//	    return apiResponse;
//	}
//
//	// Helper methods for generating different error responses
//	private ApiResponse getUnauthorizedResponse(String code, String message) {
//	    ApiResponse errorResponse = new ApiResponse();
//	    ResponseCode responseCode = new ResponseCode();
//	    responseCode.setCode("401");
//	    responseCode.setMessage(message);
//	    errorResponse.setResponseCode(responseCode);
//	    return errorResponse;
//	}
//
//	private ApiResponse getNotFoundResponse(String code, String message) {
//	    ApiResponse errorResponse = new ApiResponse();
//	    ResponseCode responseCode = new ResponseCode();
//	    responseCode.setCode("404");
//	    responseCode.setMessage(message);
//	    errorResponse.setResponseCode(responseCode);
//	    return errorResponse;
//	}
//
//	private ApiResponse getInternalErrorResponse() {
//	    ApiResponse errorResponse = new ApiResponse();
//	    ResponseCode responseCode = new ResponseCode();
//	    responseCode.setCode("500");
//	    responseCode.setMessage("An error occurred while logging in");
//	    errorResponse.setResponseCode(responseCode);
//	    return errorResponse;
//	}

//	@Override
//	public ApiResponse login(LoginRequestDtoFCD request, String jwt) {
//		ApiResponse apiResponse = new ApiResponse();
//		ResponseCode responseCode = new ResponseCode();
//		try {
//		    Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());
//
//		    if (existingUser.isPresent()) {
//		        UserFCD user = existingUser.get();
//		        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//		            responseCode.setCode("401");
//		            responseCode.setMessage("Invalid credentials");
//		            responseCode.setDescription("Incorrect password");
//		        } else {
//		            responseCode.setCode("200");
//		            responseCode.setMessage("User logged in successfully");
//
//		            ResponseData responseData = new ResponseData();
//		            // Set whatever data you need in ResponseData
//
//		            // apiResponse.setToken(jwt); // Set token in ApiResponse
//
//		            List<ResponseData> responseDataList = new ArrayList<>();
//		            responseDataList.add(responseData);
//		            apiResponse.setResponseData(responseDataList);
//		        }
//		    } else {
//		        responseCode.setCode("404");
//		        responseCode.setMessage("User not found");
//		        responseCode.setDescription("No user exists with this email");
//		    }
//		} catch (Exception e) {
//		    responseCode.setCode("500");
//		    responseCode.setMessage("An error occurred while logging in");
//		    responseCode.setDescription("Internal server error");
//		}
//		return apiResponse;
//	}
//10 12 
//		try {
//			Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());
//
//			if (existingUser.isPresent()) {
//				UserFCD user = existingUser.get();
//				if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//					responseCode.setCode("401");
//					responseCode.setMessage("Invalid credentials");
//				} else {
//					responseCode.setCode("200");
//					responseCode.setMessage("User logged in successfully");
//
//					ResponseData responseData = new ResponseData();
//					// Set whatever data you need in ResponseData
//
//					// apiResponse.setToken(jwt); // Set token in ApiResponse
//
//					List<ResponseData> responseDataList = new ArrayList<>();
//					responseDataList.add(responseData);
//					apiResponse.setResponseData(responseDataList);
//				}
//			} else {
//				responseCode.setCode("404");
//				responseCode.setMessage("User not found by this email or login");
//			}
//
//		} catch (Exception e) {
//			responseCode.setCode("500");
//			responseCode.setMessage("An error occurred while logging in");
//		}
//
//		apiResponse.setResponseCode(responseCode);
//		return apiResponse;
//	}

	// private AtomicLong sequenceNumberGenerator = new AtomicLong(1);
////login user 8 12 2023
//	@Override
//	public ApiResponse login(LoginRequestDtoFCD request) {
//		ApiResponse apiResponse = new ApiResponse();
//		ResponseCode responseCode = new ResponseCode();
//
//		try {
//			Optional<UserFCD> existingUser = userRepo.findByEmail(request.getEmail());
//
//			if (existingUser.isPresent()) {
//				UserFCD user = existingUser.get();
//				if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//					responseCode.setCode("401");
//					responseCode.setMessage("Invalid credentials");
//					apiResponse.setResponseCode(responseCode);
//				} else {
//					responseCode.setCode("200");
//					responseCode.setMessage("User logged in successfully");
//					// Set whatever data you need in ResponseData
//
//					ResponseData responseData = new ResponseData();
//					// Set whatever data you need in ResponseData
//
//					List<ResponseData> responseDataList = new ArrayList<>();
//					responseDataList.add(responseData);
//
//					apiResponse.setResponseCode(responseCode);
//					apiResponse.setResponseData(responseDataList);
//				}
//			} else {
//
//				ResponseData responseData = new ResponseData();
//				// Set whatever data you need in ResponseData
//
//				List<ResponseData> responseDataList = new ArrayList<>();
//				responseDataList.add(responseData);
//
//				apiResponse.setResponseCode(responseCode);
//				apiResponse.setResponseData(responseDataList);
//
//				responseCode.setCode("404");
//				responseCode.setMessage("User not found by this email or login");
//				apiResponse.setResponseCode(responseCode);
//			}
//		} catch (Exception e) {
//			responseCode.setCode("500");
//			responseCode.setMessage("An error occurred while logging in");
//			apiResponse.setResponseCode(responseCode);
//		}
//
//		return apiResponse;
//	}
	// forgot password
	public ResponseEntity<String> forgotPassword(String email) {
		try {
			UserFCD user = userRepo.findByEmail(email)
					.orElseThrow(() -> new UserNotFoundExceptionFCD("User not found with this email: " + email));

			String otp = generateAndSaveOTP(email);

			emailUtil.sendSetPasswordEmail(email, otp);

			return ResponseEntity.ok("Please check your email to set a new password for your account.");
		} catch (UserNotFoundExceptionFCD ex) {
			throw ex;
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send set password email. Please try again later.");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong: " + e.getMessage());
		}
	}

	private String generateAndSaveOTP(String email) {
		// Check if a user with the given email already exists
		Optional<UserFCD> existingUser = userRepo.findByEmail(email);
		if (existingUser.isPresent()) {
			// User with the given email already exists, update the OTP for that user
			UserFCD existingUserEntity = existingUser.get();
			String otp = emailUtil.generateOTP();
			existingUserEntity.setOtp(otp);
			userRepo.save(existingUserEntity);
			return otp;
		} else {
			// User with the given email does not exist, create a new OTP entity
			String otp = emailUtil.generateOTP();
			UserFCD otpEntity = new UserFCD();
			otpEntity.setEmail(email);
			otpEntity.setOtp(otp);
			userRepo.save(otpEntity);
			return otp;
		}
	}
	// forgot password 17/01/2024
//	public ResponseEntity<String> forgotPassword(String email) {
//		try {
//			UserFCD user = userRepo.findByEmail(email)
//					.orElseThrow(() -> new UserNotFoundExceptionFCD("User not found with this email: " + email));
//
//			String otp = generateAndSaveOTP(email);
//
//			emailUtil.sendSetPasswordEmail(email, otp);
//			
//		
//			return ResponseEntity.ok("Please check your email to set a new password for your account.");
//		} catch (UserNotFoundExceptionFCD ex) {
//			throw ex; 
//		} catch (MessagingException e) {
//			throw new RuntimeException("Unable to send set password email. Please try again later.");
//		} catch (Exception e) {
//			throw new RuntimeException("Something went wrong: " + e.getMessage());
//		}
//	}
//
//	private String generateAndSaveOTP(String email) {
//		String otp = emailUtil.generateOTP();
//		UserFCD otpEntity = new UserFCD();
//		otpEntity.setEmail(email);
//		otpEntity.setOtp(otp);
//		userRepo.save(otpEntity);
//		return otp;
//	}
/////////SET password
//	public ResponseEntity<String> setPassword(String email, String otp, String newPassword) {
//	    try {
//	        // Implement logic to verify OTP before setting the new password
//	        Optional<UserFCD> userList = userRepo.findByEmail(email);
//	        if (userList.isEmpty()) {
//	            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//	        }
//
//	        if (userList.size() > 1) {
//	            // Log an error (you may want to handle this differently based on your use case)
//	            log.error("Multiple users found with the same email: {}", email);
//	            return new ResponseEntity<>("An error occurred: Multiple users with the same email", HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//
//	        UserFCD user = userList.get(0);
//	        String storedOtp = user.getOtp();
//
//	        if (!otp.equals(storedOtp)) {
//	            return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
//	        }
//
//	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	        String hashedPassword = passwordEncoder.encode(newPassword);
//
//	        user.setPassword(hashedPassword);
//	        user.setOtp(null);
//
//	        userRepo.save(user);
//
//	        return new ResponseEntity<>("New Password set successfully. Login with the new password.", HttpStatus.OK);
//	    } catch (Exception e) {
//	        // Log the exception details for debugging purposes
//	        log.error("An error occurred while setting password for user with email {}: {}", email, e.getMessage(), e);
//	        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//set password
	public ResponseEntity<String> setPassword(String email, String otp, String newPassword) {
		try {
			// Implement logic to verify OTP before setting the new password
			Optional<UserFCD> userOptional = userRepo.findByotp(otp);
			if (userOptional.isEmpty()) {
				return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			}

			UserFCD user = userOptional.get();
			String storedOtp = user.getOtp();

			if (!otp.equals(storedOtp)) {
				return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
			}

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(newPassword);

			user.setPassword(hashedPassword);
			user.setOtp(null);

			userRepo.save(user);

			return new ResponseEntity<>("New Password set successfully. Login with the new password.", HttpStatus.OK);
		} catch (Exception e) {
			// Log the exception details for debugging purposes
			log.error("An error occurred while setting password for user with email {}: {}", email, e.getMessage(), e);
			return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	public ResponseEntity<String> setPassword(String email, String otp, String newPassword) {
//	    try {
//	        // Implement logic to verify OTP before setting the new password
//	        Optional<UserFCD> userOptional = userRepo.findByEmail(email);
//	        if (userOptional.isEmpty()) {
//	            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//	        }
//
//	        UserFCD user = userOptional.get();
//	        String storedOtp = user.getOtp(); // Assuming you have an 'otp' field in your UserFCD entity
//
//	        if (!otp.equals(storedOtp)) {
//	            return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
//	        }
//
//	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	        String hashedPassword = passwordEncoder.encode(newPassword);
//
//	        user.setPassword(hashedPassword);
//	        // Assuming you clear the stored OTP after successful password reset
//	        user.setOtp(null);
//
//	        userRepo.save(user);
//
//	        return new ResponseEntity<>("New Password set successfully. Login with the new password.", HttpStatus.OK);
//	    } catch (Exception e) {
//	        // Log the exception details for debugging purposes
//	        e.printStackTrace();
//	        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}

//	public ResponseEntity<String> setPassword(String email, String otp, String newPassword) {
//	    // Implement logic to verify OTP before setting the new password
//	    Optional<UserFCD> userOptional = userRepo.findByEmail(email);
//	    if (userOptional.isEmpty()) {
//	        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//	    }
//
//	    UserFCD user = userOptional.get();
//	    String storedOtp = user.getOtp(); // Assuming you have an 'otp' field in your UserFCD entity
//
//	    if (!otp.equals(storedOtp)) {
//	        return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
//	    }
//
//	    try {
//	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	        String hashedPassword = passwordEncoder.encode(newPassword);
//
//	        user.setPassword(hashedPassword);
//	        // Assuming you clear the stored OTP after successful password reset
//	        user.setOtp(null);
//
//	        userRepo.save(user);
//
//	        return new ResponseEntity<>("New Password set successfully. Login with the new password.", HttpStatus.OK);
//	    } catch (Exception e) {
//	        // Log the exception details for debugging purposes
//	        e.printStackTrace();
//	        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}

//	public ResponseEntity<String> setPassword(String email, String otp, String newPassword) {
//		// Implement logic to verify OTP before setting the new password
//		Optional<UserFCD> otpset = userRepo.findByEmail(email);
//		if (otpset == null || !otpset.get().equals(otp)) {
//			return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
//		}
//
//		try {
//			UserFCD user = userRepo.findByEmail(email)
//					.orElseThrow(() -> new UserNotFoundExceptionFCD("User not found for email: " + email));
//
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String hashedPassword = passwordEncoder.encode(newPassword);
//
//			user.setPassword(hashedPassword);
//			userRepo.save(user);
//
//			return new ResponseEntity<>("New Password set successfully. Login with the new password.", HttpStatus.OK);
//		} catch (UserNotFoundExceptionFCD e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		} catch (Exception e) {
//			// Log the exception details for debugging purposes
//			e.printStackTrace();
//			return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@Override
	public UserFCD findByusername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
// OTPEntity otpEntity = new OTPEntity(email, otp);
// otpRepository.save(otpEntity);
// return otp;

//16012024
//		public ResponseEntity<String> forgotPassword(String email) {
//		    try {
//		        UserFCD user = userRepo.findByEmail(email)
//		                .orElseThrow(() -> new UserNotFoundExceptionFCD("User not found with this email: " + email));
//		        emailUtil.sendSetPasswordEmail(email);
//		        return ResponseEntity.ok("Please check your email to set a new password for your account.");
//		    } catch (UserNotFoundExceptionFCD ex) {
//		        throw ex; // or handle accordingly if additional information is needed
//		    } catch (MessagingException e) {
//		        throw new RuntimeException("Unable to send set password email. Please try again later.");
//		    } catch (Exception e) {
//		        throw new RuntimeException("Something went wrong: " + e.getMessage());
//		    }
//		}
//forgot password 0601
//	public String forgotPassword(String email) {
//		UserFCD user = userRepo.findByEmail(email)
//				.orElseThrow(() -> new RuntimeException("User not found with this email" + email));
//		try {
//			emailUtil.sendSetPasswordEmail(email);
//		} catch (MessagingException e) {
//			throw new RuntimeException("unable to send set password email please try again");
//		}
//		return "please check your email to set new password to your account";
//	}

//	public String setPassword(String email, String newPassword) {
//		UserFCD user = userRepo.findByEmail(email)
//				.orElseThrow(() -> new RuntimeException("User not found with this email" + email));
//
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(newPassword);
//		user.setPassword(newPassword);
//		userRepo.save(user);
//
//		return "New password set successfully login with new password";
//	}

//setpassword 16/01/2024
//	public ResponseEntity<String> setPassword(String email, String newPassword) {
//		if (email == null || email.isEmpty() || newPassword == null || newPassword.isEmpty()) {
//			return new ResponseEntity<>("Email or new password is empty", HttpStatus.BAD_REQUEST);
//		}
//
//		try {
//			UserFCD user = userRepo.findByEmail(email)
//					.orElseThrow(() -> new UserNotFoundExceptionFCD("User not found for email: " + email));
//
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String hashedPassword = passwordEncoder.encode(newPassword);
//
//			user.setPassword(hashedPassword);
//			userRepo.save(user);
//
//			return new ResponseEntity<>("New Password set successfully. Login with the new password.", HttpStatus.OK);
//		} catch (UserNotFoundExceptionFCD e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		} catch (Exception e) {
//			// Log the exception details for debugging purposes
//			e.printStackTrace();
//			return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
