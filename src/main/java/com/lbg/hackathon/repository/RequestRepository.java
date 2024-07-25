package com.lbg.hackathon.repository;

import com.lbg.hackathon.entity.RequestDetails;
import com.lbg.hackathon.entity.TeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestDetails, Long> {

}
