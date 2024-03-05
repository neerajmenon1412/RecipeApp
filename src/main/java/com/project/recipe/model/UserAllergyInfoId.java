package com.project.recipe.model;

import java.io.Serializable;
import java.util.Objects;

public class UserAllergyInfoId implements Serializable {

    private Long userId;
    private Long allergenId;

    public UserAllergyInfoId() {
    }

    public UserAllergyInfoId(Long userId, Long allergenId) {
        this.userId = userId;
        this.allergenId = allergenId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAllergenId() {
        return allergenId;
    }

    public void setAllergenId(Long allergenId) {
        this.allergenId = allergenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAllergyInfoId that = (UserAllergyInfoId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(allergenId, that.allergenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, allergenId);
    }
}

