package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.Evaluation;
import com.appstra.aspirante.repository.EvaluationRepository;
import com.appstra.aspirante.service.EvaluationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EvaluationImpl implements EvaluationService {
    private final EvaluationRepository evaluationRepository;

    public EvaluationImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        evaluation.setEvaluationCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        evaluation.setEvaluationEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation) {
        Evaluation existingEvaluation = evaluationRepository.findById(evaluation.getEvaluationId())
                .orElseThrow(() -> new IllegalArgumentException("La evaluación no existe: " + evaluation.getEvaluationId()));
        evaluation.setEvaluationCreationDate(existingEvaluation.getEvaluationCreationDate());
        evaluation.setEvaluationEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Boolean deleteEvaluation(Integer evaluationId) {
        if (evaluationRepository.existsById(evaluationId)) {
            evaluationRepository.deleteById(evaluationId);
            return true;
        }
        return false;
    }

    @Override
    public List<Evaluation> listEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public Evaluation getEvaluation(Integer evaluationId) {
        return evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new NoSuchElementException("La evaluación con el ID: " + evaluationId + " no se encontró"));
    }
}

