package com.project.recipe.model;

import java.io.Serializable;
import java.util.Objects;

public class RecipeAllergyInfoId implements Serializable {

    private Long recipeId;
    private Long allergenId;

    public RecipeAllergyInfoId() {
    }

    public RecipeAllergyInfoId(Long recipeId, Long allergenId) {
        this.recipeId = recipeId;
        this.allergenId = allergenId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getAllergenId() {
        return allergenId;
    }

    public void setAllergenId(Long allergenId) {
        this.allergenId = allergenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeAllergyInfoId that = (RecipeAllergyInfoId) o;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(allergenId, that.allergenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, allergenId);
    }
}
