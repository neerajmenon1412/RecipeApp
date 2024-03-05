    package com.project.recipe.repository;

    import com.project.recipe.model.Recipe;
    import jakarta.transaction.Transactional;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Modifying;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface RecipeRepository extends JpaRepository<Recipe, Long> {
        List<Recipe> findByUserId(Long userId);

        @Modifying
        @Transactional
        @Query("UPDATE Recipe r SET r.averageRating = :average, r.ratingCount = :count WHERE r.recipeId = :recipeId")
        void updateRatingAndCount(@Param("recipeId") Long recipeId, @Param("average") double average, @Param("count") int count);
    }


