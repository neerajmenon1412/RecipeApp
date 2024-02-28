package com.project.recipe.controller;

import com.project.recipe.model.User;
import com.project.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerNewUserAccount(user);
            if (registeredUser != null) {
                System.out.println("Returning 201 CREATED status code");
                // This will return a 201 status code
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
            } else {
                System.out.println("User Registration Failed!");
                // If for some reason the user is not saved to the database, you should handle this case as well.
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/profile")
    public ResponseEntity<User> getCurrentUserProfile(Principal principal) {
        String email = principal.getName(); // Get the email from the principal object
        User user = userService.findByEmail(email);

        if (user != null) {
            return ResponseEntity.ok(user); // Return user data as JSON
        } else {
            return ResponseEntity.notFound().build(); // User not found response
        }
    }



    // Additional endpoints (like login, profile update) can be added here
}
