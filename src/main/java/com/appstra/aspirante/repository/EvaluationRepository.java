package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
    List<Evaluation> findByAspirantAspirantId (Integer aspirantId);
}
