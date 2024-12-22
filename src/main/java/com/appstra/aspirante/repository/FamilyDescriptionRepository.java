package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.DescriptionFamily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyDescriptionRepository extends JpaRepository<DescriptionFamily,Integer> {
    List<DescriptionFamily> findByAspirantAspirantId (Integer aspirantId);
}
