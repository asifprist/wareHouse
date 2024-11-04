package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.services.ClientService;

//@RestController
//@RequestMapping("/h")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	
	
}

//
//	@RequestMapping(value = "/q", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> saveClient(@RequestBody ClientDetail client) {
//	try {
//		ClientDetail save = clientService.save(client);
//
//		return ResponseHandlerFCD.generateResponse("Client detail added Successfully..", HttpStatus.OK, "Correct", save);
//
//	} 
//	catch (UserAlreadyExistsExceptionFCD ex) {
//		return ResponseHandlerFCD.generateResponse("User with this email already exists", HttpStatus.BAD_REQUEST,
//				"", null);
//	} 
//	catch (Exception e) {
//		//log.error("Error occurred during user registration: {}", e.getMessage(), e);
//		return ResponseHandlerFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", null);
//	}
//}
//	
//	@RequestMapping(value = "/s", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> getAllCLient() {
//
//		try {
//			List<ClientDetail> all = clientService.getAll();
//
//			return ResponseHandlerGetFCD.generateResponse("Client detail  ..", HttpStatus.OK, "Correct", all);
//
//		} 
//		catch (UserAlreadyExistsExceptionFCD ex) {
//			return ResponseHandlerFCD.generateResponse("User with this email already exists", HttpStatus.BAD_REQUEST,
//					"", null);
//		}
//		catch (Exception e) {
//			//log.error("Error occurred during user registration: {}", e.getMessage(), e);
//			return ResponseHandlerGetFCD.generateResponse("Something went wrong...", HttpStatus.BAD_REQUEST, "null", e);
//		}
//		
//	}
//
//	@RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> updateClient(@RequestBody ClientDetail up) {
//		try {
//		ClientDetail updateCllient = clientService.updateCllient(up);
//		return ResponseHandlerFCD.generateResponse("Client detail updated", HttpStatus.OK, "Correct", updateCllient);
//
//	}
//		catch(Exception e) {
//			return ResponseHandlerFCD.generateResponse("something went wrong",HttpStatus.OK, "null", e);
//		}
//			
//		}
//}

//@PostMapping("/q")
//@RequestMapping(value = "/q", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//public ResponseEntity<Object> saveClient(@RequestBody ClientDetail client) {
//
//	ClientDetail save = clientService.save(client);
//	
//	return new ResponseEntity<Object>(save, HttpStatus.CREATED);
//
//}
	//@GetMapping("/s")
//	@RequestMapping(value = "/s", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<ClientDetail>> getAllCLient() {
//
//		List<ClientDetail> all = clientService.getAll();
//		return new ResponseEntity<List<ClientDetail>>(all, HttpStatus.OK);
//	}

	//@GetMapping("/cl/{clId}")
//	@RequestMapping("/cl/{clId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<ClientDetail> getClientId(@PathVariable("clId") int clientId) {
//		ClientDetail clId = clientService.getClientId(clientId);
//		return new ResponseEntity<ClientDetail>(clId, HttpStatus.OK);
//	}
	
//	@GetMapping("/cl/{name}")
//	public ResponseEntity<ClientDetail> getClientByName(@PathVariable("name") String clie) {
//		ClientDetail clId = clientService.getClientName(clie);
//		return new ResponseEntity<ClientDetail>(clId, HttpStatus.OK);
//	}
	

//	@DeleteMapping("/delete/{cld}")
//	public ResponseEntity<Void> deleteById(@PathVariable("cld") int clids) {
//
//		clientService.deleteClientId(clids);
//
//		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
//
//	}
//


