package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.DescriptionFamily;
import com.appstra.aspirante.service.FamilyDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/v1/FamilyDescription")
public class FamilyDescriptionController {
    private final FamilyDescriptionService familyDescriptionService;

    public FamilyDescriptionController(FamilyDescriptionService familyDescriptionService) {
        this.familyDescriptionService = familyDescriptionService;
    }

    @PostMapping("/savefamilydescription")
    @Operation(summary = "Guardar Descripción Familiar", description = "Guardar nueva descripción familiar")
    public ResponseEntity<DescriptionFamily> saveFamilyDescription(@Validated @RequestBody DescriptionFamily familyDescription) {
        return ResponseEntity.ok(familyDescriptionService.saveFamilyDescription(familyDescription));
    }

    @PutMapping("/updatefamilydescription")
    @Operation(summary = "Actualizar Descripción Familiar", description = "Actualizar descripción familiar existente")
    public ResponseEntity<DescriptionFamily> updateFamilyDescription(@Validated @RequestBody DescriptionFamily familyDescription) {
        return ResponseEntity.ok(familyDescriptionService.updateFamilyDescription(familyDescription));
    }

    @DeleteMapping("/deletefamilydescription/{familyDescriptionId}")
    @Operation(summary = "Eliminar Descripción Familiar", description = "Eliminar descripción familiar por ID")
    public ResponseEntity<Boolean> deleteFamilyDescription(@PathVariable("familyDescriptionId") Integer familyDescriptionId) {
        return ResponseEntity.ok(familyDescriptionService.deleteFamilyDescription(familyDescriptionId));
    }

    @GetMapping("/listfamilydescriptions")
    @Operation(summary = "Lista de Descripciones Familiares", description = "Obtener lista de todas las descripciones familiares")
    public ResponseEntity<List<DescriptionFamily>> listFamilyDescriptions() {
        return ResponseEntity.ok(familyDescriptionService.listFamilyDescriptions());
    }

    @GetMapping("/{familyDescriptionId}")
    @Operation(summary = "Información de la Descripción Familiar", description = "Obtener información de la descripción familiar por ID")
    public ResponseEntity<DescriptionFamily> getFamilyDescription(@PathVariable("familyDescriptionId") Integer familyDescriptionId) {
        return ResponseEntity.ok(familyDescriptionService.getFamilyDescription(familyDescriptionId));
    }

    @GetMapping("/getFamilyDescriptionAspirant/{aspirantId}")
    @Operation(summary = "Listar Descripciones Familiares por aspirante", description = "Obtener información de la descripción familiar por ID de aspirante")
    public ResponseEntity<List<DescriptionFamily>> getFamilyDescriptionAspirant(@PathVariable("aspirantId") Integer aspirantId) {
        return ResponseEntity.ok(familyDescriptionService.getFamilyDescriptionAspirant(aspirantId));
    }
}

