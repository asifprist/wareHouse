package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.entity.ClientDetail;
import com.example.entity.RegistrationRequestFCD;
import com.example.entity.UserFCD;
import com.example.exception.UserAlreadyExistsExceptionFCD;
import com.example.exception.UserNotFoundExceptionFCD;
import com.example.jwtToken.JwtToken;
import com.example.requestdto.AuthRequest;
import com.example.requestdto.LoginRequestDtoFCD;
import com.example.response.ApiResponse;
import com.example.response.RegistrationResponseDtoFCD;
import com.example.response.ResponseCode;
import com.example.responseHandler.ResponseHandlerFCD;
import com.example.responseHandler.ResponseHandlerGetFCD;
import com.example.services.ClientService;
import com.example.services.TokenBlacklistService;
import com.example.services.UserFCDservice;
import com.mongodb.MongoException;

import io.swagger.annotations.ApiOperation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins ="localhost:3000")
@WebServlet
@Slf4j
@RestController
@RequestMapping("/student")
public class RegistrationControllerFCD {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private UserFCDservice userServicefcd;
	@Autowired
	private JwtToken jwttoken;
	@Autowired
	private ClientService clientService;

	@Autowired
    private TokenBlacklistService tokenBlacklistService;
	// @PreAuthorize("")
//	@PostMapping("/dashboard")
	//@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> dashpage(Principal p) {
//		return ResponseEntity.ok("welcome [" + p.getName() + "]");
//	}
//	@PostMapping("/blog/post/{pid}/{ptitle}")
//	public void create(@PathVariable("pid") String pid, @PathVariable("ptitle") String ptitle) {
//	// a repo save call
//	}

	// @PostMapping("/signup")
	@ApiOperation(value = "POST", notes = "API for registration")
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerUser(@Valid @RequestBody RegistrationRequestFCD registrationRequest,
			final HttpServletRequest request) {

		try {
			RegistrationResponseDtoFCD user = userServicefcd.registerUser(registrationRequest);

			return ResponseHandlerFCD.generateResponse("User Registered Successfully..", HttpStatus.OK, "Correct", user);

		} catch (UserAlreadyExistsExceptionFCD ex) {
			return ResponseHandlerFCD.generateResponse("User with this email already exists", HttpStatus.BAD_REQUEST,
					"null", null);
		} catch (MongoException ex) {
		    log.error("MongoDB error during user registration: {}", ex.getMessage(), ex);
		    return ResponseHandlerFCD.generateResponse("MongoDB error occurred...", HttpStatus.INTERNAL_SERVER_ERROR, "null", null);
		} catch (Exception e) {
		    log.error("Error occurred during user registration: {}", e.getMessage(), e);
		    return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
	}

	

//	@ApiOperation(value = "POST", notes = "API for save the user detail")
//	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequestDtoFCD loginRequest) {
//		log.info("API Request to login user and fetch the details of user");
//		try {
//			 LoginResponseDtoFCD login = userServicefcd.login(loginRequest);
//
//			return ResponseHandlerFCD.generateResponse("User logged in successfully..", HttpStatus.OK, "Correct", login);
//
//		} catch (UserAlreadyExistsExceptionFCD ex) {
//			return ResponseHandlerFCD.generateResponse("User with this email already exists", HttpStatus.BAD_REQUEST,
//					"null", null);
//		} catch (MongoException ex) {
//		    log.error("MongoDB error during user registration: {}", ex.getMessage(), ex);
//		    return ResponseHandlerFCD.generateResponse("MongoDB error occurred...", HttpStatus.INTERNAL_SERVER_ERROR, "null", null);
//		} catch (Exception e) {
//		    log.error("Error occurred during user registration: {}", e.getMessage(), e);
//		    return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//		}
//	}
	@ApiOperation(value = "POST", notes = "API for save the user detail")
	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequestDtoFCD loginRequest) {
		log.info("API Request to login user and fetch the details of user");
		try {
			ApiResponse apiResponse = userServicefcd.login(loginRequest);

			if (apiResponse.getResponseCode().getCode().equals("200")) {
				return ResponseEntity.ok(apiResponse);
			} else if (apiResponse.getResponseCode().getCode().equals("404")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
			} else if (apiResponse.getResponseCode().getCode().equals("401")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
			}
		} catch (Exception e) {
			ApiResponse errorResponse = new ApiResponse();
			ResponseCode responseCode = new ResponseCode();
			responseCode.setCode("500");
			responseCode.setMessage("An error occurred while logging in");
			errorResponse.setResponseCode(responseCode);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

//	@PostMapping("/signin")
//	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequestDtoFCD loginRequest) {
//	    log.info("API Request to login user and fetch the details of user");
//
//	    try {
//	        // Authentication
//	        Authentication authentication = authenticationManager.authenticate(
//	            new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
//	        );
//
//	        // Set as security context (authenticate)
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        // Generate JWT Token
//	        String jwt = jwttoken.generateToken(getUser());
//
//	        // Current user object
//	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//	        // Call userServicefcd login method
//	        ApiResponse apiResponse = userServicefcd.login(loginRequest, jwt);
//	        //ApiResponse apiResponse = userServicefcd.login(loginRequest);
//
//	        return ResponseEntity.ok(apiResponse);
//	    } catch (BadCredentialsException e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("401");
//	        responseCode.setMessage("Invalid credentials");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//	    } catch (LockedException e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("401");
//	        responseCode.setMessage("Account locked. Please contact support.");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//	    } catch (DisabledException e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("401");
//	        responseCode.setMessage("Account disabled. Please contact support.");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//	    } catch (Exception e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("500");
//	        responseCode.setMessage("An error occurred while logging in");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//	    }
//	}

	// 10/12
//	@PostMapping("/loginUser")
//	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequestDtoFCD loginRequest) {
//	    log.info("API Request to login user and fetch the details of user");
//
//	    try {
//	        // Authentication
//	        Authentication authentication = authenticationManager.authenticate(
//	            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//	        );
//
//	        // Set as security context (authenticate)
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        // Generate JWT Token
//	        String jwt = jwttoken.generateToken(getUser());
//
//	        // Current user object
//	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//	        // Call userServicefcd login method
//	        ApiResponse apiResponse = userServicefcd.login(loginRequest, jwt);
//
//	        return ResponseEntity.ok(apiResponse);
//	    } catch (org.springframework.security.core.AuthenticationException e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("401");
//	        responseCode.setMessage("Authentication failed");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//	    } catch (Exception e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("500");
//	        responseCode.setMessage("An error occurred while logging in");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//	    }
//	}

	// 8 12 2023
//	@PostMapping("/loginUser")
//	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequestDtoFCD loginRequest) {
//	    log.info("API Request to login user and fetch the details of user");
//
//	    try {
//	        // Authentication
//	        Authentication authentication = authenticationManager
//	                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
//
//	        // Set as security context (authenticate)
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        // Generate JWT Token
//	      //  String jwt = jwttoken.buildToken(authentication);
//
//	        // Current user object
//	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//	        // Create ApiResponse
//	        ApiResponse apiResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//
//	        // Constructing ApiResponse based on the response code
//	        if (apiResponse.getResponseCode().getCode().equals("200")) {
//	            apiResponse = userServicefcd.login(loginRequest); // Assuming userServicefcd returns ApiResponse
//	           // apiResponse.setToken(jwt); // Set token in ApiResponse
//	        } else if (apiResponse.getResponseCode().getCode().equals("404")) {
//	            responseCode.setCode("404");
//	            responseCode.setMessage("User not found");
//	        } else if (apiResponse.getResponseCode().getCode().equals("401")) {
//	            responseCode.setCode("401");
//	            responseCode.setMessage("Unauthorized");
//	        } else {
//	            responseCode.setCode("500");
//	            responseCode.setMessage("Internal Server Error");
//	        }
//
//	        apiResponse.setResponseCode(responseCode);
//	        return ResponseEntity.ok(apiResponse);
//
//	    } catch (org.springframework.security.core.AuthenticationException e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("401");
//	        responseCode.setMessage("Authentication failed");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//	    } catch (Exception e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("500");
//	        responseCode.setMessage("An error occurred while logging in");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//	    }
//	}

//	@PostMapping("/log")
//	public ResponseEntity<Object> login(@RequestBody LoginRequestDtoFCD loginreq) {
//
//		// check authentication
//		Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(loginreq.getUsername(), loginreq.getPassword()));
//
//		// set as securitycontex (authenticate)
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		// Generate JWT Token
//		String jwt = jwttoken.buildToken(authentication);
//
//		// current user object
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//		// return response
//		return ResponseEntity.ok(new JwtuserFCDRes(jwt, // token
//				userDetails.getUsername(), // username
//				userDetails.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toSet()) // Set<String>
//		));
//
//	}
//
//	@PostMapping("/loginUser")
//	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequestDtoFCD loginRequest) {
//		log.info("API Request to login user and fetch the details of user");
//
//		try {
//			ApiResponse apiResponse = userServicefcd.login(loginRequest);
//
//			if (apiResponse.getResponseCode().getCode().equals("200")) {
//				return ResponseEntity.ok(apiResponse);
//			} else if (apiResponse.getResponseCode().getCode().equals("404")) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
//			} else if (apiResponse.getResponseCode().getCode().equals("401")) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
//			} else {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
//			}
//		} catch (Exception e) {
//			ApiResponse errorResponse = new ApiResponse();
//			ResponseCode responseCode = new ResponseCode();
//			responseCode.setCode("500");
//			responseCode.setMessage("An error occurred while logging in");
//			errorResponse.setResponseCode(responseCode);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//		}
//	}
//
//	@PutMapping("/forgot")
//	public ResponseEntity<Object> forgotPassword(@RequestParam String email) {
//	    try {
//	        String forgotPasswordResponse = userServicefcd.forgotPassword(email);
//
//	        ApiResponse apiResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//
//	        // Assuming successful response returns a specific code or message
//	        if (forgotPasswordResponse.equals("success_code")) {
//	            responseCode.setCode("200");
//	            responseCode.setMessage("Password reset instructions sent successfully");
//	            apiResponse.setResponseCode(responseCode);
//	            return ResponseEntity.ok(apiResponse);
//	        } else {
//	            // Handle other specific cases if needed
//	            responseCode.setCode("404"); // Or any appropriate code
//	            responseCode.setMessage("Email not found or error in resetting password");
//	            apiResponse.setResponseCode(responseCode);
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
//	        }
//	    } catch (Exception e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("500");
//	        responseCode.setMessage("An error occurred while processing forgot password request");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//	    }
//	}
//	@PutMapping("/setPassword")
//	public ResponseEntity<Object> setPassword(@RequestParam String email, @RequestHeader String newPassword) {
//	    try {
//	        ResponseEntity<String> response = userServicefcd.setPassword(email, newPassword);
//	        String setPasswordResponse = response.getBody();
//
//	        ApiResponse apiResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//
//	        // Assuming successful response returns a specific code or message
//	        if (setPasswordResponse.equals("success_code")) {
//	            responseCode.setCode("200");
//	            responseCode.setMessage("Password updated successfully");
//	            apiResponse.setResponseCode(responseCode);
//	            return ResponseEntity.ok(apiResponse);
//	        } else {
//	            // Handle other specific cases if needed
//	            responseCode.setCode("404"); // Or any appropriate code
//	            responseCode.setMessage("Error in updating password");
//	            apiResponse.setResponseCode(responseCode);
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
//	        }
//	    } catch (Exception e) {
//	        ApiResponse errorResponse = new ApiResponse();
//	        ResponseCode responseCode = new ResponseCode();
//	        responseCode.setCode("500");
//	        responseCode.setMessage("An error occurred while setting the password");
//	        errorResponse.setResponseCode(responseCode);
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//	    }
//	}

	// @PutMapping("/forgot")
	@ApiOperation(value = "PUT", notes = "API for forgot password")
	@RequestMapping(value = "/forgot", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> forgotPassword(@RequestParam String email) {
	    try {
	        ResponseEntity<String> message = userServicefcd.forgotPassword(email);
	        return ResponseHandlerFCD.generateResponse("Password reset email sent successfully.", HttpStatus.OK, "Correct", message);
	    } catch (UserNotFoundExceptionFCD ex) {
	        return ResponseHandlerFCD.generateResponse("User with this email does not exist.", HttpStatus.NOT_FOUND, "null", null);
	    } catch (RuntimeException e) {
	        return ResponseHandlerFCD.generateResponse("Something went wrong: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "null", null);
	    }
	}
//	public ResponseEntity<Object> forgotPassword(@RequestParam String email) {
//		try {
//			String message = userServicefcd.forgotPassword(email);
//			return ResponseHandlerFCD.generateResponse("Password reset email sent successfully.", HttpStatus.OK, "",
//					message);
//		} catch (UserNotFoundExceptionFCD ex) {
//			return ResponseHandlerFCD.generateResponse("User with this email does not exist.", HttpStatus.NOT_FOUND, "",
//					null);
//		} catch (Exception e) {
//			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "", null);
//		}
//	}

//	@PutMapping("/forgot")
//	public ResponseEntity<String> forgotPassword(@RequestParam String email) {
//		return new ResponseEntity<>(userServicefcd.forgotPassword(email), HttpStatus.OK);
//	}

//	@PutMapping("/setPassword")
//	public ResponseEntity<String> setPassword(@RequestParam String email, @RequestHeader String newPassword) {
//		return new ResponseEntity<String>(HttpStatus.OK);
//	}
	@RequestMapping(value = "/setPassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> setPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
	    try {
	        ResponseEntity<String> setPasswordResponse = userServicefcd.setPassword(email, otp, newPassword);
	        return ResponseHandlerFCD.generateResponse("Password set successfully.", HttpStatus.OK, "Correct", setPasswordResponse.getBody());
	    } catch (UserNotFoundExceptionFCD ex) {
	        return ResponseHandlerFCD.generateResponse("User with this email does not exist.", HttpStatus.NOT_FOUND, "null", null);
	    } catch (Exception e) {
	        return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
	    }
	}

	
//	@ApiOperation(value = "PUT", notes = "API for setPassword")
//	@RequestMapping(value = "/setPassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	
//	public ResponseEntity<Object> setPassword(@RequestParam String email, @RequestParam String otp,@RequestParam String newPassword) {
//		try {
//			ResponseEntity<String> setPassword = userServicefcd.setPassword(email,otp,newPassword);
//			return ResponseHandlerFCD.generateResponse("Password set successfully.", HttpStatus.OK, "Correct", null);
//		} catch (UserNotFoundExceptionFCD ex) {
//			return ResponseHandlerFCD.generateResponse("User with this email does not exist.", HttpStatus.NOT_FOUND, "null",
//					null);
//		} catch (Exception e) {
//			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//		}
//	}

//	@PutMapping("/setPassword")
//	public ResponseEntity<String> setPassword(@RequestParam String email, @RequestHeader String newPassword) {
//	    ResponseEntity<String> response = userServicefcd.setPassword(email, newPassword);
//	    return response;
//	}

//	@PostMapping("/token")
//	public ResponseEntity<?> logins(@RequestBody JwtUserFCDReq userReq) {
//		String token = jwttoken.genToken(getUser());
//
//		return ResponseEntity.ok(new JwtuserFCDRes());
//	}
	
	//----------------authenticate--User----------------------------
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwttoken.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
	 
	@RequestMapping(value="/logout", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
		try {
	        String username = jwttoken.extractUsername(token);
	        tokenBlacklistService.addToBlacklist(token);
     return ResponseHandlerFCD.generateResponse("Logout Successful",HttpStatus.OK, "Correct", username);
		} catch(Exception e) {
    	 return ResponseHandlerFCD.generateResponse("Something went wrong",HttpStatus.BAD_REQUEST, "null", null);
     }
	       // return ResponseEntity.ok("Logout successful");
	    }
	
	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")

	// @GetMapping("/userlist")
//	@ApiOperation(value = "POST", notes = "API for get userlist")
//	@RequestMapping(value = "/userlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<UserFCD> getUsers() {
//		
//		
//		return userServicefcd.getUsers();
//	}
	@ApiOperation(value = "Get", notes = "API for get userlist")
	@RequestMapping(value = "/userlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsers() {
		try {
			List<UserFCD> alluser = userServicefcd.getUsers();

			return ResponseHandlerGetFCD.generateResponse("User data added..", HttpStatus.OK, "Correct", alluser);
		}
		catch (Exception e) {
			
			return ResponseHandlerGetFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
	}

	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "POST", notes = "API for get sbi cibil")
	@RequestMapping(value = "/sbi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUser() {
		System.out.println("inside get sbi controller");

		String url = "https://homeloans.sbi/getcibil"; // api end point RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		String response = restTemplate.getForObject(url, String.class); // api callusing rest client
		return response;
	}

	
//-----------------------cmr---------------------------------------------
	
	@RequestMapping(value = "/saveClient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveClient(@RequestBody ClientDetail client) {
		try {
			ClientDetail save = clientService.save(client);

			return ResponseHandlerFCD.generateResponse("Client detail added Successfully..", HttpStatus.OK, "Correct",
					save);
		}
		catch (Exception e) {
			
			return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}
	}
	@RequestMapping(value = "/getAllClient", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllCLient() {
		try {
			List<ClientDetail> all = clientService.getAll();

			return ResponseHandlerGetFCD.generateResponse("Client details  ..", HttpStatus.OK, "Correct", all);
		} catch (Exception e) {
		
			return ResponseHandlerGetFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
		}

	}
	@RequestMapping(value = "/updateClient", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateClient(@RequestBody ClientDetail client) {
		try {
		//ClientDetail updateCllient = clientService.updateCllient(up);
			ClientDetail save = clientService.save(client);
		return ResponseHandlerFCD.generateResponse("Client detail updated", HttpStatus.OK, "Correct", save);
	}
		catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong",HttpStatus.OK, "null", null);
		}
			
		}

	
	@RequestMapping(value = "/getClientById/{clId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getClientId(@PathVariable("clId") String clientId) {
		try {
			
		ClientDetail cl = clientService.getClientId(clientId);
		return ResponseHandlerFCD.generateResponse("Client details  ..", HttpStatus.OK, "Correct", cl);
	}catch (NumberFormatException e) {
        return ResponseHandlerFCD.generateResponse("Invalid client ID format...", HttpStatus.BAD_REQUEST, "null", null);
    }
		catch (Exception e) {
	
		return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
	}
	}
	@RequestMapping(value = "/getByClientName/{clName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getClientName(@PathVariable("clName") String clientName){
		try {
			ClientDetail clName = clientService.getClientName(clientName);
			return ResponseHandlerFCD.generateResponse("client Name detail", HttpStatus.OK, "Correct", clName);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
	}
	
	@RequestMapping(value = "/deleteByClientId/{clDel}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteClientId(@PathVariable("clDel")String cldel) {
		try {
			ClientDetail deleteClientId = clientService.deleteClientId(cldel);
			return ResponseHandlerFCD.generateResponse("Client Id deleted Successfully", HttpStatus.OK, "Correct", deleteClientId);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
		
	}
	
	@RequestMapping(value = "/deleteByClientName/{clDelName}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteClientName(@PathVariable("clDelName")String cldelname) {
		try {
			ClientDetail deleteClientName = clientService.deleteClientName(cldelname);
			return ResponseHandlerFCD.generateResponse("Client name deleted Successfully", HttpStatus.OK, "Correct", deleteClientName);
		}catch(Exception e) {
			return ResponseHandlerFCD.generateResponse("something went wrong", HttpStatus.BAD_REQUEST, "null", null);
		}
		
	}
	
	
	
	public String applicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
