package com.imrandan.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imrandan.user_management.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
