package com.imrandan.user_management;

public class User {
    
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Constructor for storing user info
    public User(String username, String firstName, String lastName, String email, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber
    }
}
