package com.lbg.hackathon.repository;

import com.lbg.hackathon.model.ERoleEmployee;
import com.lbg.hackathon.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {

	Optional<EmployeeRole> findByName(ERoleEmployee name);

}
