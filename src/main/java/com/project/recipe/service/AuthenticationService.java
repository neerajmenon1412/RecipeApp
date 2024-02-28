package com.project.recipe.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.project.recipe.model.User;
import com.project.recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final Algorithm algorithm;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        // Use a secure, randomly generated secret key in a real application
        this.algorithm = Algorithm.HMAC256("secret");
    }

    public String generateToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour expiration
                .sign(algorithm);
    }

    public User validateTokenAndGetUser(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String email = jwt.getSubject();
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token");
        }
    }

    public String authenticateUser(String email, String password) {
        // 1. Validate the email and password with your user's credentials.
        // This typically involves fetching the user from the database
        // by email and then comparing the hashed passwords.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. If authentication is successful, generate a token.
        // If authentication fails, throw an exception or return null.
        if (passwordEncoder.matches(password, user.getPassword())) {
            return generateToken(email);
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }
}
