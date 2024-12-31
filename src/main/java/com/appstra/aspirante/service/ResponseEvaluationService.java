package com.appstra.aspirante.service;

import com.appstra.aspirante.dto.AnswerEvaluationDTO;
import com.appstra.aspirante.dto.QualificationEvaluationDTO;
import com.appstra.aspirante.entity.ResponseEvaluation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ResponseEvaluationService {
    @Transactional
    Boolean saveResponseEvaluation(List<AnswerEvaluationDTO> answerEvaluationDTO);
    ResponseEvaluation updateResponseEvaluation(ResponseEvaluation responseEvaluation);
    List<ResponseEvaluation> listResponseEvaluations();
    ResponseEvaluation getResponseEvaluation(Integer responseEvaluationId);
    List<Map<String, Object>> qualificationEvaluation (QualificationEvaluationDTO qualificationEvaluationDTO);
    Map<String, Object> QualificationEvaluationPersonalidad (Integer evaluationId);
    List<Map<String, Object>> qualificationEvaluationPersonId (Integer personId);
    Map<String, Object> QualificationEvaluationPersonalidadPersonId (Integer personId);
}

