package com.project.recipe.controller;

import com.project.recipe.dto.AuthTokenResponse;
import com.project.recipe.dto.LoginDto;
import com.project.recipe.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService; // Service to authenticate users and issue tokens

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDetails) {
        // Assuming the AuthenticationService returns a token or an appropriate response object
        String token = authenticationService.authenticateUser(loginDetails.getEmail(), loginDetails.getPassword());
        // You need to create a response object that includes the token
        // For example, AuthTokenResponse could be a DTO that encapsulates the JWT token
        return ResponseEntity.ok(new AuthTokenResponse(token));
    }
}