package com.project.recipe.controller;

import com.project.recipe.dto.ReviewDto;
import com.project.recipe.model.RecipeRating;
import com.project.recipe.repository.ReviewRepository;
import com.project.recipe.service.RecipeRatingService;
import com.project.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository recipeRatingRepository;

    @Autowired
    private RecipeRatingService recipeRatingService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto) {
        RecipeRating recipeRating = new RecipeRating();
        recipeRating.setRecipeId(reviewDto.getRecipeId());
        recipeRating.setUserId(reviewDto.getUserId());
        recipeRating.setRating(reviewDto.getRating());
        recipeRating.setReview(reviewDto.getReview());
        recipeRating.setCreatedAt(LocalDateTime.now());

        // Save the new review
        recipeRatingRepository.save(recipeRating);

        // Update the average rating and count in the related recipe
        recipeRatingService.updateRecipeRatingAndCount(reviewDto.getRecipeId(), reviewDto.getRating());

        return ResponseEntity.ok("Review saved successfully");
    }
}
