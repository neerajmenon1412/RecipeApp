package com.project.recipe.repository;

import com.project.recipe.model.AllergyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyInfoRepository extends JpaRepository<AllergyInfo, Long> {
    // You can define custom query methods here if needed
}
