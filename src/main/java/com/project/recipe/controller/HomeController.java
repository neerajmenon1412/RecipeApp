package com.project.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recipe.model.Recipe;
import com.project.recipe.service.RecipeService;

@CrossOrigin
@RestController
@RequestMapping("/home")
public class HomeController {
    
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/allergy/{userId}")
    public ResponseEntity<List<Recipe>> getRecipesByUserAllergies(@PathVariable Long userId) {
        List<Recipe> recipes = recipeService.getRecipesByUserAllergies(userId);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/category/{userId}")
    public ResponseEntity<List<Recipe>> getRecipesByUserCategories(@PathVariable Long userId) {
        List<Recipe> recipes = recipeService.getRecipesByUserCategories(userId);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Recipe>> getPopularRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipesSortedByPopularity();
        return ResponseEntity.ok(recipes);
    }
}
