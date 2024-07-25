package com.lbg.hackathon.repository;

import com.lbg.hackathon.entity.BusinessUnit;
import com.lbg.hackathon.entity.TeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessUnit, Long> {

}
