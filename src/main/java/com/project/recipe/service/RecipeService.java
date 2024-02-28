package com.project.recipe.service;

import com.project.recipe.model.Recipe;
import com.project.recipe.model.User;
import com.project.recipe.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe, User user) {
        recipe.setUser(user); // Assuming setUser sets the owner of the recipe
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long recipeId, Recipe recipeDetails, User currentUser) {
        // Fetch the existing recipe
        Recipe existingRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + recipeId));

        // Check if the currentUser is the owner of the recipe
        if (!existingRecipe.getUser().equals(currentUser)) {
            throw new AccessDeniedException("User does not have permission to update this recipe");
        }

        // Proceed with the update if currentUser is the owner
        existingRecipe.setTitle(recipeDetails.getTitle());
        existingRecipe.setDescription(recipeDetails.getDescription());
        existingRecipe.setIngredients(recipeDetails.getIngredients());
        existingRecipe.setCookingSteps(recipeDetails.getCookingSteps());
        // Update any other fields as necessary

        return recipeRepository.save(existingRecipe);
    }

    public List<Recipe> getUserRecipes(User user) {
        return recipeRepository.findByUserId(user.getId());
    }
}
