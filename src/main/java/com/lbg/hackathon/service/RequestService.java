package com.lbg.hackathon.service;

import com.lbg.hackathon.entity.BusinessUnit;
import com.lbg.hackathon.entity.RequestDetails;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.repository.BusinessRepository;
import com.lbg.hackathon.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	public RequestDetails saveRequestDetails(RequestDetails requestDetails) throws ResourceNotFoundException {
		return requestRepository.save(requestDetails);
	}
//
//	public Requests saveRequest(Requests request) throws CustomException {
//		return requestRepository.save(request);
//	}
}
