package com.appstra.aspirante.service;

import com.appstra.aspirante.dto.AnswerEvaluationDTO;
import com.appstra.aspirante.dto.QualificationEvaluationDTO;
import com.appstra.aspirante.entity.ResponseEvaluation;

import java.util.List;
import java.util.Map;

public interface ResponseEvaluationService {
    Boolean saveResponseEvaluation(List<AnswerEvaluationDTO> answerEvaluationDTO);
    ResponseEvaluation updateResponseEvaluation(ResponseEvaluation responseEvaluation);
    List<ResponseEvaluation> listResponseEvaluations();
    ResponseEvaluation getResponseEvaluation(Integer responseEvaluationId);
    List<Map<String, Object>> qualificationEvaluation (QualificationEvaluationDTO qualificationEvaluationDTO);
}

