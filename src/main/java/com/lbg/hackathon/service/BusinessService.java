package com.lbg.hackathon.service;

import com.lbg.hackathon.entity.BusinessUnit;
import com.lbg.hackathon.entity.TeamDetails;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.repository.BusinessRepository;
import com.lbg.hackathon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Cacheable(value = "businessDetails")
    public List<BusinessUnit> getBusinessUnitDetails() throws ResourceNotFoundException {
        System.out.println("Cache Test");
        return businessRepository.findAll();
    }

    public BusinessUnit getBusinessUnitDetails(Long id) throws ResourceNotFoundException {
        return businessRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id " + id));
    }
//
//	public Requests saveRequest(Requests request) throws CustomException {
//		return requestRepository.save(request);
//	}
}
