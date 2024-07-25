package com.lbg.hackathon.repository;

import com.lbg.hackathon.entity.RequestDetails;
import com.lbg.hackathon.entity.TeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<RequestDetails, Long> {

    @Query("select u from RequestDetails u where u.requestCreatedBy =:#{#requestCreatedBy}")
    Optional<List<RequestDetails>> findByRequestCreatedBy(@Param("requestCreatedBy") Long requestCreatedId);

    @Query("select u from RequestDetails u where u.approverId =:#{#approverId} and u.status = 'CREATED'")
    Optional<List<RequestDetails>> findByApproverId(@Param("approverId") Long approverId);

}
