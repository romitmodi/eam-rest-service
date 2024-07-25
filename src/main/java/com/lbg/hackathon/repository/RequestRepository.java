package com.lbg.hackathon.repository;

import com.lbg.hackathon.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Requests, Long> {

}
