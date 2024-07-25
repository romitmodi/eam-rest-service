package com.lbg.hackathon.service;

import com.lbg.hackathon.entity.TeamDetails;
import com.lbg.hackathon.exception.ResourceNotFoundException;
import com.lbg.hackathon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Cacheable(value = "teamDetails")
    public List<TeamDetails> getTeamDetails() throws ResourceNotFoundException {
        System.out.println("Cache Test");
        return teamRepository.findAll();
    }

    public TeamDetails getTeamDetails(Long id) throws ResourceNotFoundException {
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id " + id));
    }
//
//	public Requests saveRequest(Requests request) throws CustomException {
//		return requestRepository.save(request);
//	}
}
