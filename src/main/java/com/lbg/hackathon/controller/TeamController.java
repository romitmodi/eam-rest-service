package com.lbg.hackathon.controller;

import com.lbg.hackathon.entity.Requests;
import com.lbg.hackathon.entity.TeamDetails;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/getTeams")
    public ResponseEntity<List<TeamDetails>> getTeams() throws ResourceNotFoundException {

        List<TeamDetails> teamDetailsList = teamService.getTeamDetails();
        if(teamDetailsList == null) {
            throw new ResourceNotFoundException("Resource Products Not found");
        }
        return new ResponseEntity<>(teamDetailsList, HttpStatus.ACCEPTED);

    }
}
