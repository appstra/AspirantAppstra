package com.appstra.aspirante.controller;

import com.appstra.aspirante.dto.EmployeeDTO;
import com.appstra.aspirante.entity.Evaluation;
import com.appstra.aspirante.service.EvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/saveevaluation")
    @Operation(summary = "Guardar Evaluación", description = "Guardar nueva evaluación")
    public ResponseEntity<Evaluation> saveEvaluation(@Validated @RequestBody Evaluation evaluation){
        return ResponseEntity.ok(evaluationService.saveEvaluation(evaluation));
    }

    @PutMapping("/updateevaluation")
    @Operation(summary = "Actualizar Evaluación", description = "Actualizar evaluación existente")
    public ResponseEntity<Evaluation> updateEvaluation(@Validated @RequestBody Evaluation evaluation){
        return ResponseEntity.ok(evaluationService.updateEvaluation(evaluation));
    }

    @DeleteMapping("/deleteevaluation/{evaluationId}")
    @Operation(summary = "Eliminar Evaluación", description = "Eliminar evaluación por ID")
    public ResponseEntity<Boolean> deleteEvaluation(@PathVariable("evaluationId") Integer evaluationId){
        return ResponseEntity.ok(evaluationService.deleteEvaluation(evaluationId));
    }

    @GetMapping("/listevaluations")
    @Operation(summary = "Lista de Evaluaciones", description = "Obtener lista de todas las evaluaciones")
    public ResponseEntity<List<Evaluation>> listEvaluations(){
        return ResponseEntity.ok(evaluationService.listEvaluations());
    }

    @GetMapping("/{evaluationId}")
    @Operation(summary = "Información de la Evaluación", description = "Obtener información de la evaluación por ID")
    public ResponseEntity<Evaluation> getEvaluation(@PathVariable("evaluationId") Integer evaluationId){
        return ResponseEntity.ok(evaluationService.getEvaluation(evaluationId));
    }

    @GetMapping("getEvaluationAspirant/{personId}")
    @Operation(summary = "Listar Evaluaciones por personaID", description = "Obtener información de la evaluación por ID de la persona")
    public ResponseEntity<List<Evaluation>> getEvaluationAspirant(@PathVariable("personId") Integer personId){
        return ResponseEntity.ok(evaluationService.getEvaluationPerson(personId));
    }

    @GetMapping("/stateContratationAspirants/{aspirantId}")
    @Operation(summary = "estado de contratacion", description = "Estado de contratacion del aspirantes")
    public ResponseEntity<EmployeeDTO> stateContratationAspirants(@PathVariable("aspirantId") Integer aspirantId){
        return ResponseEntity.ok(evaluationService.stateContratationAspirants(aspirantId));
    }
}

