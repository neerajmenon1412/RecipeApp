package com.project.recipe.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Allergy_info")
public class AllergyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergen_id")
    private Long id;
    @Column(name = "allergen_name")
    private String preferenceName;

    @ManyToMany(mappedBy = "allergyInfo")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public void setPreferenceName(String preferenceName) {
        this.preferenceName = preferenceName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

