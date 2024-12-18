package com.appstra.aspirante.service;

import com.appstra.aspirante.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    Evaluation saveEvaluation(Evaluation evaluation);
    Evaluation updateEvaluation(Evaluation evaluation);
    Boolean deleteEvaluation(Integer evaluationId);
    List<Evaluation> listEvaluations();
    Evaluation getEvaluation(Integer evaluationId);
    List<Evaluation> getEvaluationAspirant (Integer evaluationId);
}
