package com.project.recipe.repository;

import com.project.recipe.model.RecipeRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<RecipeRating, Long> {
    List<RecipeRating> findByRecipeId(Long recipeId);
}
