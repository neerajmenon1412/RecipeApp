package com.project.recipe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_category")
@IdClass(UserCategoryId.class)
public class UserCategory {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    // Constructors

    public UserCategory() {
    }

    public UserCategory(Long userId, Long categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
