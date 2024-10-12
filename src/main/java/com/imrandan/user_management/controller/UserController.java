package com.imrandan.user_management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imrandan.user_management.model.User;
import com.imrandan.user_management.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // The Root Handle Path
    @GetMapping("/")
    public String home() {
        return "Welcome to the User Management Application!";
    }

    // Creates a new user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Gets all users
    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Get a user by their ID
    @GetMapping("/users/{id}") 
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get()); //this will return the user if found
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // else return a 404
        }
    }

    // Updates user
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            return userRepository.save(user);
        }
        return null;
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById((id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // should return 204 No Content after successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //returns 404 if user is not found
        }
    }
}
