package com.lbg.hackathon.controller;

import com.lbg.hackathon.entity.Requests;
import com.lbg.hackathon.exception.CustomException;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping("/getRequests")
	public ResponseEntity<List<Requests>> getProducts() throws ResourceNotFoundException {
	
			List<Requests> requestsList = requestService.getRequests();
			if(requestsList == null) {
				throw new ResourceNotFoundException("Resource Products Not found");
			}
			return new ResponseEntity<>(requestsList,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getRequests/{id}")
	//@PreAuthorize("hasRole(ROLE_ADMIN)")
	public ResponseEntity<Requests> getProduct(@PathVariable("id") Long id) throws ResourceNotFoundException {
	
			Requests request = requestService.getRequest(id);
			return new ResponseEntity<>(request,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/postRequest")
	public ResponseEntity<Requests> postProduct(@RequestBody Requests request) throws CustomException {
		Requests product1 = requestService.saveRequest(request);
		return new ResponseEntity<>(product1,HttpStatus.ACCEPTED);
	}

}
