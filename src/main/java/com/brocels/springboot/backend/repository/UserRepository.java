package com.brocels.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brocels.springboot.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
