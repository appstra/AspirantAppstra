package com.appstra.aspirante.service;

import com.appstra.aspirante.dto.AnswerEvaluationDTO;
import com.appstra.aspirante.entity.ResponseEvaluation;

import java.util.List;

public interface ResponseEvaluationService {
    Boolean saveResponseEvaluation(List<AnswerEvaluationDTO> answerEvaluationDTO);
    ResponseEvaluation updateResponseEvaluation(ResponseEvaluation responseEvaluation);
    List<ResponseEvaluation> listResponseEvaluations();
    ResponseEvaluation getResponseEvaluation(Integer responseEvaluationId);
}

