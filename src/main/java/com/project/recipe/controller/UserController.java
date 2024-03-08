package com.project.recipe.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recipe.dto.RegistrationDto;
import com.project.recipe.model.User;
import com.project.recipe.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
        try{
            User user = new User();
            user.setEmail(registrationDto.getEmail());
            user.setName(registrationDto.getName());
            user.setPassword(registrationDto.getPassword());
            user.setBio(registrationDto.getBio());
            User registeredUser = userService.registerNewUserAccount(user); // Implement this method in UserService to handle registration
            if (registeredUser == null) {
                return new ResponseEntity<>("User registration failed", HttpStatus.BAD_REQUEST);
            }
            System.out.println(registrationDto.getAllergies()+"\n*******************************************************");
            for (Long allergenId : registrationDto.getAllergies()) {
                System.out.println(allergenId + " - "+registeredUser.getId()+"********************************");
                jdbcTemplate.update("INSERT INTO user_allergy_info (user_id, allergen_id) VALUES (?, ?)", registeredUser.getId(), allergenId);
            }
            System.out.println(registrationDto.getCategories()+"\n*******************************************************");
            for (Long categoryId : registrationDto.getCategories()) {
                System.out.println(categoryId + " - "+registeredUser.getId()+"********************************");
                jdbcTemplate.update("INSERT INTO user_category (user_id, category_id) VALUES (?, ?)", registeredUser.getId(), categoryId);
            }             
            return ResponseEntity.status(HttpStatus.CREATED).body("User has been registered successfully!");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user\n"+e);
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
