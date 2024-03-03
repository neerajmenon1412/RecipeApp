package com.project.recipe.dto;

import java.util.List;

public class RecipeDto {
    private String title;
    private String description;
    private Long userId;
    private String image1Url;
    private List<Long> allergies;
    private List<Long> categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImage1Url() {
        return image1Url;
    }

    public void setImage1Url(String image1Url) {
        this.image1Url = image1Url;
    }

    public List<Long> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Long> allergies) {
        this.allergies = allergies;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}
