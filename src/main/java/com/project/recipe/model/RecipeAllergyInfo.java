//package com.project.recipe.model;
//
//import jakarta.persistence.*;
//
//import java.io.Serializable;
//
//@Entity
//@Table(name = "recipe_allergy_info")
//public class RecipeAllergyInfo  {
//
//    @EmbeddedId
//    private Long id;
//
//    @ManyToOne
//    @MapsId("recipeId") // This corresponds to attribute in RecipeDietaryPreferenceId
//    @JoinColumn(name = "recipe_id")
//    private Recipe recipe;
//
//    @ManyToOne
//    @MapsId("preferenceId") // This corresponds to attribute in RecipeDietaryPreferenceId
//    @JoinColumn(name = "allergen_id")
//    private AllergyInfo allergyInfo;
//
//    // Constructors, getters, and setters
//
//    // If you are using a composite key, you would also have a static inner class here for that key
//    @Embeddable
//    public static class RecipeDietaryPreferenceId implements Serializable {
//        private Long recipeId;
//        private Long preferenceId;
//
//        public RecipeDietaryPreferenceId(Long recipeId, Long preferenceId) {
//            this.recipeId = recipeId;
//            this.preferenceId = preferenceId;
//        }
//
//        public Long getRecipeId() {
//            return recipeId;
//        }
//
//        public void setRecipeId(Long recipeId) {
//            this.recipeId = recipeId;
//        }
//
//        public Long getPreferenceId() {
//            return preferenceId;
//        }
//
//        public void setPreferenceId(Long preferenceId) {
//            this.preferenceId = preferenceId;
//        }
//    }
//}
