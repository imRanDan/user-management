package com.imrandan.user_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.imrandan.user_management.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
