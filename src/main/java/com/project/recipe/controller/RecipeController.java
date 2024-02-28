package com.project.recipe.controller;

import com.project.recipe.model.Recipe;
import com.project.recipe.model.User;
import com.project.recipe.service.RecipeService;
import com.project.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe, Principal principal) {
        // Retrieve the authenticated user from the principal
        User user = userService.findByEmail(principal.getName());
        Recipe createdRecipe = recipeService.createRecipe(recipe, user);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipeDetails, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Recipe updatedRecipe = recipeService.updateRecipe(recipeId, recipeDetails, user);
        return ResponseEntity.ok(updatedRecipe);
    }


    @GetMapping("/user")
    public ResponseEntity<List<Recipe>> getUserRecipes(Principal principal) {
        // Retrieve the authenticated user from the principal
        User user = userService.findByEmail(principal.getName());
        List<Recipe> recipes = recipeService.getUserRecipes(user);
        return ResponseEntity.ok(recipes);
    }

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
