package com.lbg.hackathon.repository;

import java.util.Optional;

import com.lbg.hackathon.entity.ERole;
import com.lbg.hackathon.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query("select u from Role u where u.name =:#{#name}")
	Optional<Role> findByName(@Param("name") ERole name);

	@Query("select u from Role u where u.name =:#{#name}")
	Optional<Role> findByRoleName(@Param("name") String name);

}
