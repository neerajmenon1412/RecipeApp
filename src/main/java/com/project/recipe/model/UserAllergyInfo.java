package com.project.recipe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_allergy_info")
@IdClass(UserAllergyInfoId.class)
public class UserAllergyInfo {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "allergen_id")
    private Long allergenId;

    // Constructors

    public UserAllergyInfo() {
    }

    public UserAllergyInfo(Long userId, Long allergenId) {
        this.userId = userId;
        this.allergenId = allergenId;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAllergenId() {
        return allergenId;
    }

    public void setAllergenId(Long allergenId) {
        this.allergenId = allergenId;
    }
}
