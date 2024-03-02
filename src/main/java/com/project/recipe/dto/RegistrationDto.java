package com.project.recipe.dto;

import java.util.List;

public class RegistrationDto {
    private String name;
    private String email;
    private String password;
    private String bio;
    private List<Long> allergies;
    private List<Long> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
