package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.LaborExperience;
import com.appstra.aspirante.service.LaborExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/v1/LaborExperience")
public class LaborExperienceController {
    private final LaborExperienceService laborExperienceService;

    public LaborExperienceController(LaborExperienceService laborExperienceService) {
        this.laborExperienceService = laborExperienceService;
    }

    @PostMapping("/savelaborexperience")
    @Operation(summary = "Guardar Experiencia Laboral", description = "Guardar nueva experiencia laboral")
    public ResponseEntity<LaborExperience> saveLaborExperience(@Validated @RequestBody LaborExperience laborExperience) {
        return ResponseEntity.ok(laborExperienceService.saveLaborExperience(laborExperience));
    }

    @PutMapping("/updatelaborexperience")
    @Operation(summary = "Actualizar Experiencia Laboral", description = "Actualizar experiencia laboral existente")
    public ResponseEntity<LaborExperience> updateLaborExperience(@Validated @RequestBody LaborExperience laborExperience) {
        return ResponseEntity.ok(laborExperienceService.updateLaborExperience(laborExperience));
    }

    @DeleteMapping("/deletelaborexperience/{laborExperienceId}")
    @Operation(summary = "Eliminar Experiencia Laboral", description = "Eliminar experiencia laboral por ID")
    public ResponseEntity<Boolean> deleteLaborExperience(@PathVariable("laborExperienceId") Integer laborExperienceId) {
        return ResponseEntity.ok(laborExperienceService.deleteLaborExperience(laborExperienceId));
    }

    @GetMapping("/listlaborexperiences")
    @Operation(summary = "Lista de Experiencias Laborales", description = "Obtener lista de todas las experiencias laborales")
    public ResponseEntity<List<LaborExperience>> listLaborExperiences() {
        return ResponseEntity.ok(laborExperienceService.listLaborExperiences());
    }

    @GetMapping("/{laborExperienceId}")
    @Operation(summary = "Información de la Experiencia Laboral", description = "Obtener información de la experiencia laboral por ID")
    public ResponseEntity<LaborExperience> getLaborExperience(@PathVariable("laborExperienceId") Integer laborExperienceId) {
        return ResponseEntity.ok(laborExperienceService.getLaborExperience(laborExperienceId));
    }

    @GetMapping("/getLaborExperienceAspirant/{aspirantId}")
    @Operation(summary = "Listar Experiencias Laborales por aspirante", description = "Obtener información de la experiencia laboral por ID de aspirante")
    public ResponseEntity<List<LaborExperience>> getLaborExperienceAspirant(@PathVariable("aspirantId") Integer aspirantId) {
        return ResponseEntity.ok(laborExperienceService.getLaborExperienceAspirant(aspirantId));
    }
}

