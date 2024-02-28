package com.project.recipe.controller;

import com.project.recipe.model.User;
import com.project.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // This endpoint can be used to validate if the username/email is available
    @GetMapping("/register")
    public ResponseEntity<?> showRegistration() {
        // It could return a message or simply status OK if you just want to check the endpoint is working
        return ResponseEntity.ok("Registration endpoint is ready to receive POST requests.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserAccount(@RequestBody User user) {
        User registered = userService.registerNewUserAccount(user); // Implement this method in UserService to handle registration
        if (registered == null) {
            return new ResponseEntity<>("User registration failed", HttpStatus.BAD_REQUEST);
        }
        // Ideally, the location of the created user would be returned in the response headers
        return new ResponseEntity<>(registered, HttpStatus.CREATED); // Return the created user and status code 201
    }
}
