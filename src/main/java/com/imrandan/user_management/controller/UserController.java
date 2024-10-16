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
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // Checks for empty fields for user creation
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return new ResponseEntity<>("Username cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            return new ResponseEntity<>("First name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            return new ResponseEntity<>("Last name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return new ResponseEntity<>("Email cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
            return new ResponseEntity<>("Phone number cannot be empty", HttpStatus.BAD_REQUEST);
        }

        // Checks if a user with the same username or email already exists
        Optional<User> existingUserByUsername = userRepository.findByUsername(user.getUsername());
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> existingUserByPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber());

        if (existingUserByUsername.isPresent()) {
            return new ResponseEntity<>("Username is already taken.", HttpStatus.CONFLICT);
        }

        if (existingUserByEmail.isPresent()) {
            return new ResponseEntity<>("Email is already registered.", HttpStatus.CONFLICT);
        }

        if (existingUserByPhoneNumber.isPresent()) {
            return new ResponseEntity<>("Phone number is already registered.", HttpStatus.CONFLICT);
        }

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
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
