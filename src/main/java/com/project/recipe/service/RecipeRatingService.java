package com.project.recipe.service;

import com.project.recipe.model.RecipeRating;
import com.project.recipe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeRatingService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<RecipeRating> findRatingsByRecipeId(Long recipeId) {
        return reviewRepository.findByRecipeId(recipeId);
    }
}
