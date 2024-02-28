package com.project.recipe.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour expiration
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String validateTokenAndGetSubject(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }

    // This method is no longer needed, as it is specific to the io.jsonwebtoken library
    // public String extractUsername(String token) {
    //     return extractClaim(token, Claims::getSubject);
    // }

    // This method is no longer needed, as it is specific to the io.jsonwebtoken library
    // public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    //     final Claims claims = extractAllClaims(token);
    //     return claimsResolver.apply(claims);
    // }

    // Replace this method with one suitable for com.auth0.jwt
    public DecodedJWT extractAllClaims(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        return verifier.verify(token);
    }

    public String extractUsername(String token) {
        return validateTokenAndGetSubject(token);
    }

}
