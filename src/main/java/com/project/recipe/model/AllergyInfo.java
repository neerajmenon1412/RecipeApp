package com.project.recipe.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Allergy_info")
public class AllergyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergen_id")
    private Long allergenId;

    @Column(name = "allergen_name", nullable = false)
    private String allergenName;

    // Define the many-to-many relationship with User
    @ManyToMany(mappedBy = "allergies")
    private Set<User> users = new HashSet<>();

    // Constructors, getters, and setters
    public AllergyInfo() {
        // Default constructor required by JPA
    }

    public AllergyInfo(String allergenName) {
        this.allergenName = allergenName;
    }

    public AllergyInfo(int allergenId) {
        this.allergenId = (long) allergenId;
    }

    public Long getAllergenId() {
        return allergenId;
    }

    public void setAllergenId(Long allergenId) {
        this.allergenId = allergenId;
    }

    public String getAllergenName() {
        return allergenName;
    }

    public void setAllergenName(String allergenName) {
        this.allergenName = allergenName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}



