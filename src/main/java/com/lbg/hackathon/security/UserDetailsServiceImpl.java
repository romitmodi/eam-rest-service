//package com.lbg.hackathon.security;
//
//import com.lbg.hackathon.entity.Employee;
//import com.lbg.hackathon.entity.Users;
//import com.lbg.hackathon.repository.EmployeeRepository;
//import com.lbg.hackathon.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	@Autowired
//	EmployeeRepository userRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Employee user = userRepo.findByName(username).orElseThrow(() -> new UsernameNotFoundException("UserName not found"));
//
//		return UserDetailsImpl.build(user);
//	}
//
//}
