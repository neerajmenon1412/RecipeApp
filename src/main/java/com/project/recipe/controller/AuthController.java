package com.project.recipe.controller;

import com.project.recipe.dto.AuthTokenResponse;
import com.project.recipe.dto.LoginDto;
import com.project.recipe.model.User;
import com.project.recipe.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDetails) {
        String email = loginDetails.getEmail();
        String password = loginDetails.getPassword();

        try {
            String token = authenticationService.authenticateUser(email, password);
            User user = authenticationService.getUserByEmail(email);
            // Create a response object containing token, user_id, and name
            AuthTokenResponse response = new AuthTokenResponse(token, user.getId(), user.getName());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
