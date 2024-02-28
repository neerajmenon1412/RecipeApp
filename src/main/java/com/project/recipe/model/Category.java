package com.project.recipe.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String category;

    @ManyToMany
    @JoinTable(
            name = "Recipe_category", // Name of the join table
            joinColumns = @JoinColumn(name = "category_id"), // Column for the foreign key to this entity
            inverseJoinColumns = @JoinColumn(name = "recipe_id") // Column for the foreign key to the other entity
    )
    private Set<Recipe> recipes; // Assuming you have a many-to-many relationship in Recipe

    protected Category() {
        // for JPA
    }

    public Category(Long categoryId, String category, Set<Recipe> recipes) {
        this.categoryId = categoryId;
        this.category = category;
        this.recipes = recipes;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
