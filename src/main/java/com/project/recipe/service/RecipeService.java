package com.project.recipe.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.recipe.dto.RecipeDto;
import com.project.recipe.model.Recipe;
import com.project.recipe.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(RecipeDto recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setUserId(recipeDTO.getUserId());
        recipe.setImage1Url(recipeDTO.getImage1Url());
        recipe.setCreatedAt(LocalDateTime.now());
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found for ID: " + recipeId));
    }

    public List<Recipe> getUserRecipes(Long userId) {
        return recipeRepository.findByUserId(userId);
    }

    public Recipe updateRecipe(Long recipeId, Recipe recipeDetails) {
        // Fetch the existing recipe
        Recipe existingRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + recipeId));

        // Proceed with the update if currentUser is the owner
        existingRecipe.setTitle(recipeDetails.getTitle());
        existingRecipe.setDescription(recipeDetails.getDescription());

        return recipeRepository.save(existingRecipe);
    }

    public List<Recipe> getRecipesByUserAllergies(Long userId) {
        return recipeRepository.findRecipesByUserAllergies(userId);
    }

    public List<Recipe> getRecipesByUserCategories(Long userId) {
        return recipeRepository.findRecipesByUserCategories(userId);
    }

    // public List<Recipe> getUserRecipes(User user) {
    //     return recipeRepository.findByUserId(user.getId());
    // }
}
