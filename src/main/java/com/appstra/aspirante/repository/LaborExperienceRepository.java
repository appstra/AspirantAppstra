package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.LaborExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaborExperienceRepository extends JpaRepository<LaborExperience, Integer> {
    List<LaborExperience> findByAspirantAspirantId (Integer aspirantId);
}