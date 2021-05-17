package com.brocels.springboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brocels.springboot.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
	User findByEmail(String email);
	
	List<User> findByCountry(String country);
	
	List<User> findByState(String state);
	
}
