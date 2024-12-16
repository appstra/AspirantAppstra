package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.Ask;
import com.appstra.aspirante.service.AskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Ask")
public class AskController {
    private final AskService askService;

    public AskController(AskService askService) {
        this.askService = askService;
    }

    @PostMapping("/saveask")
    @Operation(summary = "Guardar Pregunta", description = "Guardar nueva pregunta")
    public ResponseEntity<Ask> saveAsk(@Validated @RequestBody Ask ask){
        return ResponseEntity.ok(askService.saveAsk(ask));
    }

    @PutMapping("/updateask")
    @Operation(summary = "Actualizar Pregunta", description = "Actualizar pregunta existente")
    public ResponseEntity<Ask> updateAsk(@Validated @RequestBody Ask ask){
        return ResponseEntity.ok(askService.updateAsk(ask));
    }

    @DeleteMapping("/deleteask/{askId}")
    @Operation(summary = "Eliminar Pregunta", description = "Eliminar pregunta por ID")
    public ResponseEntity<Boolean> deleteAsk(@PathVariable("askId") Integer askId){
        return ResponseEntity.ok(askService.deleteAsk(askId));
    }

    @GetMapping("/listask")
    @Operation(summary = "Lista de Preguntas", description = "Obtener lista de todas las preguntas")
    public ResponseEntity<List<Ask>> listAsk(){
        return ResponseEntity.ok(askService.listAsk());
    }

    @GetMapping("/{askId}")
    @Operation(summary = "Información de la Pregunta", description = "Obtener información de la pregunta por ID")
    public ResponseEntity<Ask> getAsk(@PathVariable("askId") Integer askId){
        return ResponseEntity.ok(askService.getAsk(askId));
    }
}

