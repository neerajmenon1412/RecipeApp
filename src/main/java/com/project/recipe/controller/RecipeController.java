package com.project.recipe.controller;

import com.project.recipe.model.Recipe;
import com.project.recipe.model.RecipeRating;
import com.project.recipe.model.User;
import com.project.recipe.service.RecipeRatingService;
import com.project.recipe.service.RecipeService;
import com.project.recipe.service.UserService;
import com.project.recipe.dto.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private RecipeRatingService recipeRatingService;

    @PostMapping("/create")
    public ResponseEntity<?> createRecipe(@RequestBody RecipeDto recipeDTO) {
        Recipe recipe = recipeService.createRecipe(recipeDTO);
        // System.out.println(recipeDTO.getAllergies()+"\n*******************************************************");
        for (Long allergenId : recipeDTO.getAllergies()) {
            // System.out.println(allergenId + " - "+recipe.getRecipeId()+"********************************");
            jdbcTemplate.update("INSERT INTO recipe_allergy_info (recipe_id, allergen_id) VALUES (?, ?)", recipe.getRecipeId(), allergenId);
        }
        // System.out.println(recipeDTO.getCategories()+"\n*******************************************************");
        for (Long categoryId : recipeDTO.getCategories()) {
            // System.out.println(categoryId + " - "+recipe.getRecipeId()+"********************************");
            jdbcTemplate.update("INSERT INTO recipe_category (recipe_id, category_id) VALUES (?, ?)", recipe.getRecipeId(), categoryId);
        }
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @PutMapping("edit/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipeDetails) {
        Recipe updatedRecipe = recipeService.updateRecipe(recipeId, recipeDetails);
        return ResponseEntity.ok(updatedRecipe);
    }

    @GetMapping("/getRecipeDetails/{recipeId}")
    public ResponseEntity<Recipe> getRecipeDetails(@PathVariable("recipeId") Long recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        if(recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUserRecipes/{userId}")
    public ResponseEntity<List<Recipe>> getUserRecipes(@PathVariable("userId") Long userId) {
        List<Recipe> recipes = recipeService.getUserRecipes(userId);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/getRecipeReviews/{recipeId}")
    public ResponseEntity<List<RecipeRating>> getRecipeReviews(@PathVariable Long recipeId) {
        List<RecipeRating> reviews = recipeRatingService.findRatingsByRecipeId(recipeId);
        return ResponseEntity.ok(reviews);
    }

    // @GetMapping("/user")
    // public ResponseEntity<List<Recipe>> getUserRecipes(Principal principal) {
    //     // Retrieve the authenticated user from the principal
    //     User user = userService.findByEmail(principal.getName());
    //     List<Recipe> recipes = recipeService.getUserRecipes(user);
    //     return ResponseEntity.ok(recipes);
    // }

    // Assuming this method is used internally and not as a request handler
    private void userAuth() {
        // Get the authenticated user's email
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Get the username/email from the authentication object

        // Use UserService to fetch the user object
        User user = userService.findByEmail(email);

        // ... use the user object as needed ...
    }
}
