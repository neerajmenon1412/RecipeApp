package com.project.recipe.controller;

import com.project.recipe.model.Recipe;
import com.project.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/api/recipes/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String query) {
        List<Recipe> recipes = recipeService.searchRecipes(query);
        return ResponseEntity.ok(recipes);
    }
}
