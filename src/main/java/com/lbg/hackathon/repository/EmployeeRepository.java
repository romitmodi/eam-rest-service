package com.lbg.hackathon.repository;

import com.lbg.hackathon.entity.Employee;
import com.lbg.hackathon.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
