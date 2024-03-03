package com.project.recipe.controller;
import com.project.recipe.dto.ReviewDto;
import com.project.recipe.model.RecipeRating;
import com.project.recipe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository recipeRatingRepository;

    @PostMapping
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto) {
        RecipeRating recipeRating = new RecipeRating();
        recipeRating.setRecipeId(reviewDto.getRecipeId());
        recipeRating.setUserId(reviewDto.getUserId());
        recipeRating.setRating(reviewDto.getRating());
        recipeRating.setReview(reviewDto.getReview());
        recipeRating.setCreatedAt(LocalDateTime.now()); // Assuming you have a date field

        recipeRatingRepository.save(recipeRating);
        return ResponseEntity.ok("Review saved successfully");
    }
}
