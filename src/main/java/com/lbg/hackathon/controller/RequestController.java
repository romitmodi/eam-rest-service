package com.lbg.hackathon.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
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

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<RequestDetails> approveRequest(@PathVariable("requestId") Long requestId) throws ResourceNotFoundException, IOException, ExecutionException, InterruptedException {
        RequestDetails requestDetails = requestRepository.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("Request Not found"));
        RequestDetails approveRequest = requestDetails;
        if(!ObjectUtils.isEmpty(requestDetails)) {
            approveRequest.setStatus(EStatus.IN_PROGRESS);
        }
        RequestDetails requestDetails1 = requestService.saveRequestDetails(approveRequest);
        PostMessageDTO postMessageDTO = PostMessageDTO.builder().messageType("CREATED").requestId(requestDetails1.getId())
                .role(requestDetails1.getRequestorRole()).teamId(requestDetails1.getRequestorTeamId()).empId(requestDetails1.getRequestCreatedBy()).build();

       // publishMessage(postMessageDTO.toString());
        String projectId = "lloyds-hack-grp-17";
        String topicId = "ticketCreateUpdate";

        publisherExample(projectId, topicId, postMessageDTO.toString());

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

    public void publisherExample(String projectId, String topicId, String message)
            throws IOException, ExecutionException, InterruptedException {
        TopicName topicName = TopicName.of(projectId, topicId);

        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Published message ID: " + messageId);
        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }

}
