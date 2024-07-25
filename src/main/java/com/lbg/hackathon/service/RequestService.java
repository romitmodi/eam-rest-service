package com.lbg.hackathon.service;

import java.util.List;

import com.lbg.hackathon.entity.Requests;
import com.lbg.hackathon.exception.CustomException;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
		
	public List<Requests> getRequests() throws ResourceNotFoundException {
		return requestRepository.findAll();
	}
	
	public Requests getRequest(Long id) throws ResourceNotFoundException {
		return requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id " + id));
	}

	public Requests saveRequest(Requests request) throws CustomException {
		return requestRepository.save(request);
	}
}
