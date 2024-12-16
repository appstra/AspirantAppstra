package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.ResponseEvaluation;

import java.util.List;

public interface ResponseEvaluationService {
    ResponseEvaluation saveResponseEvaluation(ResponseEvaluation responseEvaluation);
    ResponseEvaluation updateResponseEvaluation(ResponseEvaluation responseEvaluation);
    Boolean deleteResponseEvaluation(Integer responseEvaluationId);
    List<ResponseEvaluation> listResponseEvaluations();
    ResponseEvaluation getResponseEvaluation(Integer responseEvaluationId);
}

