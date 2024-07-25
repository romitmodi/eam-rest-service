package com.lbg.hackathon.repository;

import com.lbg.hackathon.entity.ERole;
import com.lbg.hackathon.entity.Requests;
import com.lbg.hackathon.entity.Role;
import com.lbg.hackathon.entity.TeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamDetails, Long> {

}
