package com.appstra.aspirante.implementation;

import com.appstra.aspirante.dto.EmployeeDTO;
import com.appstra.aspirante.entity.Aspirant;
import com.appstra.aspirante.entity.Evaluation;
import com.appstra.aspirante.repository.EvaluationRepository;
import com.appstra.aspirante.service.AspirantService;
import com.appstra.aspirante.service.EvaluationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EvaluationImpl implements EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final AspirantService aspirantService;

    public EvaluationImpl(EvaluationRepository evaluationRepository, AspirantService aspirantService) {
        this.evaluationRepository = evaluationRepository;
        this.aspirantService = aspirantService;
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

    @Override
    public List<Evaluation> getEvaluationPerson(Integer personId) {
        return evaluationRepository.findByPersonId(personId);
    }

    @Override
    public EmployeeDTO stateContratationAspirants(Integer aspirantId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Boolean contratacion = evaluationRepository.stateContratationAspirants(aspirantId);
        if(contratacion){
            Aspirant aspirant = aspirantService.getAspirant(aspirantId);
            aspirant.setStateId(8);
            aspirant = aspirantService.updateAspirant(aspirant);
            if(aspirant.getStateId().equals(8)){
                employeeDTO.setStateId(1);
                employeeDTO.setCompanyId(aspirant.getCompanyId());
                employeeDTO.setPersonId(aspirant.getPersonId());
                employeeDTO.setRoleId(aspirant.getRoleId());
                employeeDTO.setMesagge("Se cambio el estado al aspirante");
            }
        }else {
            employeeDTO.setMesagge("Al aspirante le falta realizar las evaluaciones");
        }
        return employeeDTO;
    }

    @Override
    public Boolean finallyEvaliation(Integer evaluationId) {
        return evaluationRepository.finallyEvaliation(evaluationId);
    }
}

