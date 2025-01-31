package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.ResponseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ResponseEvaluationRepository extends JpaRepository<ResponseEvaluation,Integer> {

    @Query(value = """
            select
            	prueba.competenceid AS "competenceid",
            	prueba.puntuacionaspirante AS "aspirantScore",
            	prueba.numeropreguntas "numberAsk",
            	prueba.porcentaje || '%' AS porcentaje,
            	tepa.tepa_description  as "testParametersDescription"
            from
            	get_calcular_puntuacion(?1) prueba
            	inner join transactional.test_parameters tepa on prueba.competenceid = tepa.comp_id
            	and Prueba.porcentaje BETWEEN tepa.tepa_value_min and tepa.tepa_value_max
            """,nativeQuery = true)
    Map<String, Object> QualificationEvaluationPersonalidad(@Param("evaluationId") Integer evaluationId);


    @Query(value = """
            SELECT
                comp.comp_id AS "competenceId",
                comp.comp_name AS "competenceName",
                calculo.competencevaluemax AS "competenceValueMax",
                COALESCE(valueResponse.responseanswersum, 0) AS "responseAnswerSum",
                CONCAT(
                    ROUND(
                        (COALESCE(valueResponse.responseanswersum, 0) * 100) / calculo.competencevaluemax
                    ),
                    '%'
                ) AS "percentage",
                tepa.tepa_description AS "testParametersDescription"
            FROM
                parameterization.evaluation eval
                INNER JOIN parameterization.type_test AS tyte ON eval.tyte_id = tyte.tyte_id
                INNER JOIN parameterization.competence AS comp ON tyte.tyte_id = comp.tyte_id
                LEFT JOIN LATERAL (
                    SELECT competencevaluemax
                    FROM public.get_max_score_competence(comp.comp_id)
                ) AS calculo ON TRUE
                LEFT JOIN LATERAL (
                    SELECT responseanswersum
                    FROM get_competence_response_summary(eval.eval_id, comp.comp_id)
                ) AS valueResponse ON TRUE
                INNER JOIN transactional.test_parameters tepa ON tepa.comp_id = comp.comp_id
                    and ROUND(
                        (COALESCE(valueResponse.responseanswersum, 0) * 100) / calculo.competencevaluemax
                ) BETWEEN tepa.tepa_value_min and tepa.tepa_value_max
            WHERE
                eval.pers_id = ?1
                AND eval.tyte_id = 1
            ORDER BY comp.comp_id ASC
            """,nativeQuery = true)
    List<Map<String, Object>> QualificationEvaluationPersonId(@Param("personId") Integer personId);

    @Query(value = """
            WITH subquery AS (
                SELECT
                    eval.eval_id
                FROM
                    parameterization.evaluation eval
                WHERE
                    eval.pers_id = ?1
                    AND eval.tyte_id = 3
            )
            SELECT
                prueba.competenceid AS "competenceid",
                prueba.puntuacionaspirante AS "aspirantScore",
                prueba.numeropreguntas AS "numberAsk",
                prueba.porcentaje || '%' AS porcentaje,
                tepa.tepa_description AS "testParametersDescription"
            FROM
                get_calcular_puntuacion((SELECT eval_id FROM subquery)) prueba
            INNER JOIN
                transactional.test_parameters tepa ON prueba.competenceid = tepa.comp_id
            WHERE
                prueba.porcentaje BETWEEN tepa.tepa_value_min AND tepa.tepa_value_max
            """,nativeQuery = true)
    Map<String, Object> QualificationEvaluationPersonalidadPersonId(@Param("personId") Integer personId);
}
