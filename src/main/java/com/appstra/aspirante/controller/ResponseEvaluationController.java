package com.appstra.aspirante.controller;

import com.appstra.aspirante.dto.AnswerEvaluationDTO;
import com.appstra.aspirante.dto.QualificationEvaluationDTO;
import com.appstra.aspirante.entity.ResponseEvaluation;
import com.appstra.aspirante.service.ResponseEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ResponseEvaluation")
public class ResponseEvaluationController {
    private final ResponseEvaluationService responseEvaluationService;

    public ResponseEvaluationController(ResponseEvaluationService responseEvaluationService) {
        this.responseEvaluationService = responseEvaluationService;
    }

    @PostMapping("/saveresponseevaluation")
    @Operation(summary = "Guardar Respuesta Evaluación", description = "Guardar nueva respuesta de evaluación")
    public ResponseEntity<Boolean> saveResponseEvaluation(@Validated @RequestBody List<AnswerEvaluationDTO> answerEvaluationDTO){
        return ResponseEntity.ok(responseEvaluationService.saveResponseEvaluation(answerEvaluationDTO));
    }

    @PutMapping("/updateresponseevaluation")
    @Operation(summary = "Actualizar Respuesta Evaluación", description = "Actualizar respuesta de evaluación existente")
    public ResponseEntity<ResponseEvaluation> updateResponseEvaluation(@Validated @RequestBody ResponseEvaluation responseEvaluation){
        return ResponseEntity.ok(responseEvaluationService.updateResponseEvaluation(responseEvaluation));
    }

    @GetMapping("/listresponseevaluations")
    @Operation(summary = "Lista de Respuestas Evaluaciones", description = "Obtener lista de todas las respuestas de evaluaciones")
    public ResponseEntity<List<ResponseEvaluation>> listResponseEvaluations(){
        return ResponseEntity.ok(responseEvaluationService.listResponseEvaluations());
    }

    @GetMapping("/{responseEvaluationId}")
    @Operation(summary = "Información de la Respuesta Evaluación", description = "Obtener información de la respuesta de evaluación por ID")
    public ResponseEntity<ResponseEvaluation> getResponseEvaluation(@PathVariable("responseEvaluationId") Integer responseEvaluationId){
        return ResponseEntity.ok(responseEvaluationService.getResponseEvaluation(responseEvaluationId));
    }

    @GetMapping("/QualificationEvaluationPersonalidad/{evaluationId}")
    @Operation(summary = "Califica Evaluación de personalidad", description = "Califica respuesta de evaluación de personalidad")
    public ResponseEntity<Map<String, Object>> QualificationEvaluationPersonalidad(@PathVariable("evaluationId") Integer evaluationId){
        return ResponseEntity.ok(responseEvaluationService.QualificationEvaluationPersonalidad(evaluationId));
    }

    @GetMapping("/QualificationEvaluationPersonId/{personId}")
    @Operation(summary = "Califica Evaluación por PersonId", description = "Califica respuesta de evaluación por PersonId")
    public ResponseEntity<List<Map<String, Object>>> qualificationEvaluationPersonId(@PathVariable("personId") Integer personId){
        return ResponseEntity.ok(responseEvaluationService.qualificationEvaluationPersonId(personId));
    }
    @GetMapping("/QualificationEvaluationPersonalidadPersonId/{personId}")
    @Operation(summary = "Califica Evaluación de personalidad por PersonId", description = "Califica respuesta de evaluación de personalidad PersonId")
    public ResponseEntity<Map<String, Object>> QualificationEvaluationPersonalidadPersonId(@PathVariable("personId") Integer personId){
        return ResponseEntity.ok(responseEvaluationService.QualificationEvaluationPersonalidadPersonId(personId));
    }
}
