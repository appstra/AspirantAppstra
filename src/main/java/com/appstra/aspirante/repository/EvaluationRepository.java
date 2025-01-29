package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {

    List<Evaluation> findByPersonId (Integer aspirantId);

    @Query(value = """
            SELECT
            	CASE
            		WHEN EXISTS (
            			SELECT 1
            			FROM parameterization.evaluation
            			WHERE pers_id = ?1 AND stat_id <> 5
            		) THEN FALSE
            		ELSE TRUE
            	END AS result
            """,nativeQuery = true)
    Boolean stateContratationAspirants(@Param("personId") Integer personId);
}
