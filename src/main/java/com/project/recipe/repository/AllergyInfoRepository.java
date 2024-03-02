package com.project.recipe.repository;

import com.project.recipe.model.AllergyInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyInfoRepository extends JpaRepository<AllergyInfo, Long> {
    Optional<AllergyInfo> findByAllergenName(String allergenName);
}
