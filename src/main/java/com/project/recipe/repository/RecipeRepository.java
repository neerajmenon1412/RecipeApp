    package com.project.recipe.repository;

    import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.recipe.model.Recipe;

import jakarta.transaction.Transactional;

@Repository
    public interface RecipeRepository extends JpaRepository<Recipe, Long> {
        List<Recipe> findByUserId(Long userId);

        @Query("SELECT r FROM Recipe r WHERE recipeId IN (SELECT recipeId FROM UserAllergyInfo uai INNER JOIN RecipeAllergyInfo r ON uai.allergenId = r.allergenId WHERE uai.userId=:userId)")
        List<Recipe> findRecipesByUserAllergies(@Param("userId") Long userId);  

        @Query("SELECT r FROM Recipe r WHERE recipeId IN (SELECT recipeId FROM UserCategory uc INNER JOIN RecipeCategory r ON uc.categoryId = r.categoryId WHERE uc.userId=:userId)")
        List<Recipe> findRecipesByUserCategories(@Param("userId") Long userId);  


        @Modifying
        @Transactional
        @Query("UPDATE Recipe r SET r.averageRating = :average, r.ratingCount = :count WHERE r.recipeId = :recipeId")
        void updateRatingAndCount(@Param("recipeId") Long recipeId, @Param("average") double average, @Param("count") int count);

        //List<Recipe> findByTitleContainingIgnoreCase(String title);

        List<Recipe> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    }


