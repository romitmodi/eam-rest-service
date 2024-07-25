package com.lbg.hackathon.repository;

import com.lbg.hackathon.entity.ERole;
import com.lbg.hackathon.entity.ERoleEmployee;
import com.lbg.hackathon.entity.EmployeeRole;
import com.lbg.hackathon.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {

	Optional<EmployeeRole> findByName(ERoleEmployee name);

}
