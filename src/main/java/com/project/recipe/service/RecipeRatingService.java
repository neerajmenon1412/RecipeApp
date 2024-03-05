package com.project.recipe.service;

import com.project.recipe.model.RecipeRating;
import com.project.recipe.repository.RecipeRepository;
import com.project.recipe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeRatingService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeRating> findRatingsByRecipeId(Long recipeId) {
        return reviewRepository.findByRecipeId(recipeId);
    }

    public void updateRecipeRatingAndCount(Long recipeId, double newRating) {
        List<RecipeRating> ratings = reviewRepository.findByRecipeId(recipeId);

        // Calculate the updated count
        int count = ratings.size();

        // Calculate the updated total rating
        double totalRating = 0;
        for (RecipeRating rating : ratings) {
            totalRating += rating.getRating();
        }

        // Check if the new rating is a duplicate
        boolean isNewRating = true;
        for (RecipeRating rating : ratings) {
            if (rating.getRating() == newRating) {
                isNewRating = false;
                break;
            }
        }

        // Add the new rating to the total rating only if it's not a duplicate
        if (isNewRating) {
            totalRating += newRating;
            // Increment count for the new rating
            count++;
        }

        // Calculate the updated average rating
        double averageRating = count == 0 ? 0 : totalRating / count;

        // Update recipe with the new average rating and count
        recipeRepository.updateRatingAndCount(recipeId, averageRating, count);
    }

}
