package com.project.recipe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_category")
@IdClass(RecipeCategoryId.class)
public class RecipeCategory {

    @Id
    @Column(name = "recipe_id")
    private Long recipeId;

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    // Constructors

    public RecipeCategory() {
    }

    public RecipeCategory(Long recipeId, Long categoryId) {
        this.recipeId = recipeId;
        this.categoryId = categoryId;
    }

    // Getters and Setters

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getAllergenId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

