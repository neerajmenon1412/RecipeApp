package com.project.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recipe.dto.RegistrationDto;
import com.project.recipe.model.User;
import com.project.recipe.service.UserService;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public ResponseEntity<?> registerUserAccount(@RequestBody RegistrationDto registrationDto) {
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
                // jdbcTemplate.update("INSERT INTO User_allergy_info (user_id, allergen_id) VALUES (?, ?)", registeredUser.getId(), allergenId);
            }            
            
            return ResponseEntity.status(HttpStatus.CREATED).body("User has been registered successfully!");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }
}
