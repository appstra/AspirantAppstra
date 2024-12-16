package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.Competence;
import com.appstra.aspirante.service.CompetenceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Competence")
public class CompetenceController {
    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @PostMapping("/savecompetence")
    @Operation(summary = "Guardar Competencia", description = "Guardar nueva competencia")
    public ResponseEntity<Competence> saveCompetence(@Validated @RequestBody Competence competence){
        return ResponseEntity.ok(competenceService.saveCompetence(competence));
    }

    @PutMapping("/updatecompetence")
    @Operation(summary = "Actualizar Competencia", description = "Actualizar competencia existente")
    public ResponseEntity<Competence> updateCompetence(@Validated @RequestBody Competence competence){
        return ResponseEntity.ok(competenceService.updateCompetence(competence));
    }

    @DeleteMapping("/deletecompetence/{competenceId}")
    @Operation(summary = "Eliminar Competencia", description = "Eliminar competencia por ID")
    public ResponseEntity<Boolean> deleteCompetence(@PathVariable("competenceId") Integer competenceId){
        return ResponseEntity.ok(competenceService.deleteCompetence(competenceId));
    }

    @GetMapping("/listcompetences")
    @Operation(summary = "Lista de Competencias", description = "Obtener lista de todas las competencias")
    public ResponseEntity<List<Competence>> listCompetences(){
        return ResponseEntity.ok(competenceService.listCompetences());
    }

    @GetMapping("/{competenceId}")
    @Operation(summary = "Información de la Competencia", description = "Obtener información de la competencia por ID")
    public ResponseEntity<Competence> getCompetence(@PathVariable("competenceId") Integer competenceId){
        return ResponseEntity.ok(competenceService.getCompetence(competenceId));
    }
}

