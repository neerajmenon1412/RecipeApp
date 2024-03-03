package com.project.recipe.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Recipe_rating")
public class RecipeRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "user_id")
    private Long userId;

    private Double rating;
    private String review;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors, getters, and setters

    public RecipeRating(Long id, Long recipeId, Long userId, Double rating, String review, LocalDateTime createdAt) {
        this.id = id;
        this.recipeId = recipeId;
        this.userId = userId;
        this.rating = rating;
        this.review = review;
        this.createdAt = createdAt;
    }

    public RecipeRating() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
