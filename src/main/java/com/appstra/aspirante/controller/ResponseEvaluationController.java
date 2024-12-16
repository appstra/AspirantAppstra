package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.ResponseEvaluation;
import com.appstra.aspirante.service.ResponseEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ResponseEvaluation")
public class ResponseEvaluationController {
    private final ResponseEvaluationService responseEvaluationService;

    public ResponseEvaluationController(ResponseEvaluationService responseEvaluationService) {
        this.responseEvaluationService = responseEvaluationService;
    }

    @PostMapping("/saveresponseevaluation")
    @Operation(summary = "Guardar Respuesta Evaluación", description = "Guardar nueva respuesta de evaluación")
    public ResponseEntity<ResponseEvaluation> saveResponseEvaluation(@Validated @RequestBody ResponseEvaluation responseEvaluation){
        return ResponseEntity.ok(responseEvaluationService.saveResponseEvaluation(responseEvaluation));
    }

    @PutMapping("/updateresponseevaluation")
    @Operation(summary = "Actualizar Respuesta Evaluación", description = "Actualizar respuesta de evaluación existente")
    public ResponseEntity<ResponseEvaluation> updateResponseEvaluation(@Validated @RequestBody ResponseEvaluation responseEvaluation){
        return ResponseEntity.ok(responseEvaluationService.updateResponseEvaluation(responseEvaluation));
    }

    @DeleteMapping("/deleteresponseevaluation/{responseEvaluationId}")
    @Operation(summary = "Eliminar Respuesta Evaluación", description = "Eliminar respuesta de evaluación por ID")
    public ResponseEntity<Boolean> deleteResponseEvaluation(@PathVariable("responseEvaluationId") Integer responseEvaluationId){
        return ResponseEntity.ok(responseEvaluationService.deleteResponseEvaluation(responseEvaluationId));
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
}
