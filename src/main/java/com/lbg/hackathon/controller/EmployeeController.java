package com.lbg.hackathon.controller;

import com.lbg.hackathon.entity.*;
import com.lbg.hackathon.model.EmployeeDetailRequest;
import com.lbg.hackathon.repository.EmployeeRepository;
import com.lbg.hackathon.repository.EmployeeRoleRepository;
import com.lbg.hackathon.repository.RoleRepository;
import com.lbg.hackathon.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/postEmployee")
    public ResponseEntity<?> saveEmployee(@Validated @RequestBody EmployeeDetailRequest employeeDetailRequest) {

        // Create new user's account
        Employee employee = new Employee(employeeDetailRequest.getEmpName());

        String roleName = employeeDetailRequest.getEmpRole();
        EmployeeRole userRole = employeeRoleRepository.findByName(ERoleEmployee.findByName(roleName))
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


        employee.setRole(userRole);
        TeamDetails teamDetails = teamService.getTeamDetails(employeeDetailRequest.getTeamId());
        employee.setTeamDetails(teamDetails);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponse("User saved successfully!"));
    }
}
