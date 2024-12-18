package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.ResponseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ResponseEvaluationRepository extends JpaRepository<ResponseEvaluation,Integer> {
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
                parameterization.aspirant AS aspi
                INNER JOIN parameterization.evaluation AS eval ON aspi.aspi_id = eval.aspi_id
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
                aspi.aspi_id = ?1
                AND eval.eval_id = ?2
            ORDER BY comp.comp_id ASC
            """,nativeQuery = true)
    List<Map<String, Object>> QualificationEvaluation(@Param("aspirantId") Integer aspirantId, @Param("evaluationId") Integer evaluationId);
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
}
