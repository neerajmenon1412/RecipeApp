package com.project.recipe.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    @Column(name = "password_hash")
    private String password;

    private String bio;

    @ManyToMany
    @JoinTable(
            name = "user_allergy_info", // The correct join table name
            joinColumns = @JoinColumn(name = "user_id"), // Correct column in join table referring to User
            inverseJoinColumns = @JoinColumn(name = "allergy_info_id") // Correct column in join table referring to AllergyInfo
    )
    private Set<AllergyInfo> allergyInfo = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<AllergyInfo> getAllergyInfo() {
        return allergyInfo;
    }

    public void setAllergyInfo(Set<AllergyInfo> allergyInfo) {
        this.allergyInfo = allergyInfo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
