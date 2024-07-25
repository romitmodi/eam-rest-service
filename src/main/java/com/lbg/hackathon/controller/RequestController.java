package com.lbg.hackathon.controller;

import com.lbg.hackathon.entity.Employee;
import com.lbg.hackathon.entity.EmployeeRole;
import com.lbg.hackathon.entity.RequestDetails;
import com.lbg.hackathon.entity.TeamDetails;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.model.*;
import com.lbg.hackathon.repository.RequestRepository;
import com.lbg.hackathon.service.TeamService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping("/postRequest")
    public ResponseEntity<?> saveRequest(@Validated @RequestBody RequestDetailsDTO requestDetailsDTO) {

        // Create new user's account
        TeamDetails teamDetails = teamService.getTeamDetails(requestDetailsDTO.getRequestorTeamId());

        RequestDetails requestDetails = new RequestDetails(requestDetailsDTO.getRequestorEmpId(),EStatus.CREATED);

        if(!ObjectUtils.isEmpty(teamDetails)) {
            requestDetails.setRequestorTeamId(teamDetails.getId());
            requestDetails.setApproverId(teamDetails.getElId());
        }
        requestDetails.setRequestorRole(requestDetailsDTO.getReqestorRole());
        requestRepository.save(requestDetails);

        return ResponseEntity.ok(new MessageResponse("User saved successfully!"));
    }

    @GetMapping("/getRequests")
    public ResponseEntity<List<RequestDetails>> getRequests() throws ResourceNotFoundException {

        List<RequestDetails> requestDetailsList = requestRepository.findAll();
        if(requestDetailsList == null) {
            throw new ResourceNotFoundException("Resource Products Not found");
        }
        return new ResponseEntity<>(requestDetailsList, HttpStatus.ACCEPTED);

    }
}
