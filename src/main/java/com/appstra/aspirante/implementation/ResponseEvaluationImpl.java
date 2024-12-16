package com.appstra.aspirante.implementation;

import com.appstra.aspirante.entity.ResponseEvaluation;
import com.appstra.aspirante.repository.ResponseEvaluationRepository;
import com.appstra.aspirante.service.ResponseEvaluationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResponseEvaluationImpl implements ResponseEvaluationService {
    private final ResponseEvaluationRepository responseEvaluationRepository;

    public ResponseEvaluationImpl(ResponseEvaluationRepository responseEvaluationRepository) {
        this.responseEvaluationRepository = responseEvaluationRepository;
    }

    @Override
    public ResponseEvaluation saveResponseEvaluation(ResponseEvaluation responseEvaluation) {
        responseEvaluation.setResponseEvaluationCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        responseEvaluation.setResponseEvaluationEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return responseEvaluationRepository.save(responseEvaluation);
    }

    @Override
    public ResponseEvaluation updateResponseEvaluation(ResponseEvaluation responseEvaluation) {
        ResponseEvaluation existingResponseEvaluation = responseEvaluationRepository.findById(responseEvaluation.getResponseEvaluationId())
                .orElseThrow(() -> new IllegalArgumentException("La evaluación de respuesta no existe: " + responseEvaluation.getResponseEvaluationId()));
        responseEvaluation.setResponseEvaluationCreationDate(existingResponseEvaluation.getResponseEvaluationCreationDate());
        responseEvaluation.setResponseEvaluationEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return responseEvaluationRepository.save(responseEvaluation);
    }

    @Override
    public Boolean deleteResponseEvaluation(Integer responseEvaluationId) {
        if (responseEvaluationRepository.existsById(responseEvaluationId)) {
            responseEvaluationRepository.deleteById(responseEvaluationId);
            return true;
        }
        return false;
    }

    @Override
    public List<ResponseEvaluation> listResponseEvaluations() {
        return responseEvaluationRepository.findAll();
    }

    @Override
    public ResponseEvaluation getResponseEvaluation(Integer responseEvaluationId) {
        return responseEvaluationRepository.findById(responseEvaluationId)
                .orElseThrow(() -> new NoSuchElementException("La evaluación de respuesta con el ID: " + responseEvaluationId + " no se encontró"));
    }
}

