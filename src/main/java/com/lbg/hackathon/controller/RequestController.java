package com.lbg.hackathon.controller;

import com.lbg.hackathon.entity.RequestDetails;
import com.lbg.hackathon.entity.TeamDetails;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.model.EStatus;
import com.lbg.hackathon.model.MessageResponse;
import com.lbg.hackathon.model.PostMessageDTO;
import com.lbg.hackathon.model.RequestDetailsDTO;
import com.lbg.hackathon.repository.RequestRepository;
import com.lbg.hackathon.service.RequestService;
import com.lbg.hackathon.service.TeamService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;


    @PostMapping("/postRequest")
    public ResponseEntity<?> saveRequest(@Validated @RequestBody RequestDetailsDTO requestDetailsDTO) {

        // Create new user's account
        TeamDetails teamDetails = teamService.getTeamDetails(requestDetailsDTO.getRequestorTeamId());

        RequestDetails requestDetails = new RequestDetails(requestDetailsDTO.getRequestorEmpId(), EStatus.CREATED);

        if (!ObjectUtils.isEmpty(teamDetails)) {
            requestDetails.setRequestorTeamId(teamDetails.getId());
            requestDetails.setApproverId(teamDetails.getElId());
        }
        requestDetails.setRequestorRole(requestDetailsDTO.getReqestorRole());
        requestService.saveRequestDetails(requestDetails);

        return ResponseEntity.ok(new MessageResponse("Request saved successfully!"));
    }

    @GetMapping("/getRequests")
    public ResponseEntity<List<RequestDetails>> getRequests() throws ResourceNotFoundException {

        List<RequestDetails> requestDetailsList = requestRepository.findAll();
        if (requestDetailsList == null) {
            throw new ResourceNotFoundException("Resource Requests Not found");
        }
        return new ResponseEntity<>(requestDetailsList, HttpStatus.ACCEPTED);

    }

    @GetMapping("/getRequests/{id}")
    public ResponseEntity<RequestDetails> getRequest(@PathVariable("id") Long id) throws ResourceNotFoundException {

        RequestDetails requestDetails = requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request Not found"));
        return new ResponseEntity<>(requestDetails, HttpStatus.ACCEPTED);

    }

    @GetMapping("/getUserRequests/{empId}")
    public ResponseEntity<List<RequestDetails>> getRequestsByUser(@PathVariable("empId") Long empId) throws ResourceNotFoundException {

        List<RequestDetails> requestDetails = requestRepository.findByRequestCreatedBy(empId).orElseThrow(() -> new ResourceNotFoundException("No Requests Created By User"));
        return new ResponseEntity<>(requestDetails, HttpStatus.ACCEPTED);

    }

    @GetMapping("/getApprovalRequests/{empId}")
    public ResponseEntity<List<RequestDetails>> getRequestsForApproval(@PathVariable("empId") Long empId) throws ResourceNotFoundException {

        List<RequestDetails> requestDetails = requestRepository.findByApproverId(empId).orElseThrow(() -> new ResourceNotFoundException("No Requests present for Approval"));
        return new ResponseEntity<>(requestDetails, HttpStatus.ACCEPTED);

    }

    @PatchMapping("/approve/{requestId}")
    public ResponseEntity<RequestDetails> approveRequest(@PathVariable("requestId") Long requestId) throws ResourceNotFoundException, IOException {
        RequestDetails requestDetails = requestRepository.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("Request Not found"));
        RequestDetails approveRequest = requestDetails;
        if(!ObjectUtils.isEmpty(requestDetails)) {
            approveRequest.setStatus(EStatus.IN_PROGRESS);
        }
        RequestDetails requestDetails1 = requestService.saveRequestDetails(approveRequest);
        PostMessageDTO postMessageDTO = PostMessageDTO.builder().messageType("CREATED").requestId(requestDetails1.getId())
                .role(requestDetails1.getRequestorRole()).teamId(requestDetails1.getRequestorTeamId()).empId(requestDetails1.getRequestCreatedBy()).build();

       // publishMessage(postMessageDTO.toString());


        return new ResponseEntity<>(requestDetails1, HttpStatus.CREATED);
    }

//    public void publishMessage(String message) throws IOException {
//
//        ByteString byteStr = ByteString.copyFrom(message, StandardCharsets.UTF_8);
//        PubsubMessage pubsubApiMessage = PubsubMessage.newBuilder().setData(byteStr).build();
//
//        Publisher publisher = Publisher.newBuilder(
//                ProjectTopicName.of("lloyds-hack-grp-17", "ticketCreateUpdate")).build();
//
//        // Attempt to publish the message
//        String responseMessage;
//        try {
//            publisher.publish(pubsubApiMessage).get();
//            responseMessage = "Message published.";
//        } catch (InterruptedException | ExecutionException e) {
//            responseMessage = "Error publishing Pub/Sub message; see logs for more info.";
//        }
//    }
}
