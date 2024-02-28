package com.project.recipe.controller;

import com.project.recipe.model.User;
import com.project.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/profile")
    public ResponseEntity<?> showUserProfile() {
        // Get the email of the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Get the username/email from the authentication object

        // Use UserService to find the user by email
        User user = userService.findByEmail(email);

        if (user != null) {
            // Return user information as JSON
            return ResponseEntity.ok(user);
        } else {
            // User not found
            return ResponseEntity.notFound().build();
        }
    }
}
