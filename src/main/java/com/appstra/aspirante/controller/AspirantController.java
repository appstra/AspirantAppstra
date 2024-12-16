package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.Aspirant;
import com.appstra.aspirante.service.AspirantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Aspirant")
public class AspirantController {
    private final AspirantService aspirantService;

    public AspirantController(AspirantService aspirantService) {
        this.aspirantService = aspirantService;
    }

    @PostMapping("/saveaspirant")
    @Operation(summary = "Guardar Aspirante", description = "Guardar nuevo aspirante")
    public ResponseEntity<Aspirant> saveAspirant(@Validated @RequestBody Aspirant aspirant){
        return ResponseEntity.ok(aspirantService.saveAspirant(aspirant));
    }

    @PutMapping("/updateaspirant")
    @Operation(summary = "Actualizar Aspirante", description = "Actualizar aspirante existente")
    public ResponseEntity<Aspirant> updateAspirant(@Validated @RequestBody Aspirant aspirant){
        return ResponseEntity.ok(aspirantService.updateAspirant(aspirant));
    }

    @DeleteMapping("/deleteaspirant/{aspirantId}")
    @Operation(summary = "Eliminar Aspirante", description = "Eliminar aspirante por ID")
    public ResponseEntity<Boolean> deleteAspirant(@PathVariable("aspirantId") Integer aspirantId){
        return ResponseEntity.ok(aspirantService.deleteAspirant(aspirantId));
    }

    @GetMapping("/listaspirants")
    @Operation(summary = "Lista de Aspirantes", description = "Obtener lista de todos los aspirantes")
    public ResponseEntity<List<Aspirant>> listAspirants(){
        return ResponseEntity.ok(aspirantService.listAspirants());
    }

    @GetMapping("/{aspirantId}")
    @Operation(summary = "Información del Aspirante", description = "Obtener información del aspirante por ID")
    public ResponseEntity<Aspirant> getAspirant(@PathVariable("aspirantId") Integer aspirantId){
        return ResponseEntity.ok(aspirantService.getAspirant(aspirantId));
    }
}

