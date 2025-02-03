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

    @Query(value = """
            	select
            		--eval.eval_id,
            		--tyte.tyte_id,
            		--tyte.tyte_name,
            		--count(ask.*):: INTEGER as "NumeroPreguntas",
            		--count(respuestaPersona.*):: INTEGER as "NumeroPreguntasRespondidas",
            		(count(ask.*) = count(respuestaPersona.*)) "finalizoPrueba"
            	from
            		parameterization.evaluation eval
            		inner join parameterization.type_test tyte on eval.tyte_id = tyte.tyte_id
            		inner join parameterization.competence comp on tyte.tyte_id = comp.tyte_id
            		inner join parameterization.ask ask on comp.comp_id = ask.comp_id
            		left join (select ask_id from transactional.response_evaluation where eval_id = ?1) respuestaPersona on ask.ask_id = respuestaPersona.ask_id
            	where
            		eval_id = ?1
            	group by
            		eval.eval_id,
            		tyte.tyte_id,
            		tyte.tyte_name
            """,nativeQuery = true)
    Boolean finallyEvaliation(@Param("evaluationId") Integer evaluationId);
}
