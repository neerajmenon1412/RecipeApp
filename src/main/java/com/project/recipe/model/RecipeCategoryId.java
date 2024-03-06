package com.project.recipe.model;

import java.io.Serializable;
import java.util.Objects;

public class RecipeCategoryId implements Serializable {

    private Long recipeId;
    private Long categoryId;

    public RecipeCategoryId() {
    }

    public RecipeCategoryId(Long recipeId, Long categoryId) {
        this.recipeId = recipeId;
        this.categoryId = categoryId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategoryId that = (RecipeCategoryId) o;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, categoryId);
    }
}
