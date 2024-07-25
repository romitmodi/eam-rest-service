package com.lbg.hackathon.controller;

import com.lbg.hackathon.entity.Employee;
import com.lbg.hackathon.entity.EmployeeRole;
import com.lbg.hackathon.entity.SignUpRequest;
import com.lbg.hackathon.entity.TeamDetails;
import com.lbg.hackathon.model.ERoleEmployee;
import com.lbg.hackathon.model.MessageResponse;
import com.lbg.hackathon.repository.EmployeeRepository;
import com.lbg.hackathon.repository.EmployeeRoleRepository;
import com.lbg.hackathon.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    TeamService teamService;


//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
//
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//		return ResponseEntity.ok(new JwtResponse(jwt,
//												 userDetails.getId().toString(),
//												 userDetails.getUsername()));
//	}

    @PostMapping("/signin")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
        if (employeeRepository.existsByName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        Employee employee = new Employee(signUpRequest.getUsername(),
                signUpRequest.getPassword(), signUpRequest.getEmail());

        String roleName = signUpRequest.getEmpRole();
        EmployeeRole userRole = employeeRoleRepository.findByName(ERoleEmployee.findByName(roleName))
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


        employee.setRole(userRole);
        TeamDetails teamDetails = teamService.getTeamDetails(signUpRequest.getTeamId());
        employee.setTeamDetails(teamDetails);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponse("User signed successfully!"));
    }

}
