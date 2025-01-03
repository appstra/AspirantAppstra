package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.TypeTest;
import com.appstra.aspirante.service.TypeTestService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/TypeTest")
public class TypeTestController {
    private final TypeTestService typeTestService;

    public TypeTestController(TypeTestService typeTestService) {
        this.typeTestService = typeTestService;
    }

    @PostMapping("/savetypeTest")
    @Operation(summary = "Guardar Tipo de Prueba", description = "Guardar un nuevo tipo de prueba")
    public ResponseEntity<TypeTest> saveTypeTest(@Validated @RequestBody TypeTest typeTest){
        return ResponseEntity.ok(typeTestService.saveTypeTest(typeTest));
    }

    @PutMapping("/updatetypeTest")
    @Operation(summary = "Actualizar Tipo de Prueba", description = "Actualizar un tipo de prueba existente")
    public ResponseEntity<TypeTest> updateTypeTest(@Validated @RequestBody TypeTest typeTest){
        return ResponseEntity.ok(typeTestService.updateTypeTest(typeTest));
    }

    @DeleteMapping("/deletetypeTest/{typeTestId}")
    @Operation(summary = "Eliminar Tipo de Prueba", description = "Eliminar un tipo de prueba por ID")
    public ResponseEntity<Boolean> deleteTypeTest(@PathVariable("typeTestId") Integer typeTestId){
        return ResponseEntity.ok(typeTestService.deleteTypeTest(typeTestId));
    }

    @GetMapping("/listtypeTests")
    @Operation(summary = "Lista de Tipos de Prueba", description = "Obtener lista de todos los tipos de prueba")
    public ResponseEntity<List<TypeTest>> listTypeTests(){
        return ResponseEntity.ok(typeTestService.listTypeTests());
    }

    @GetMapping("/{typeTestId}")
    @Operation(summary = "Información de Tipo de Prueba", description = "Obtener información de un tipo de prueba por ID")
    public ResponseEntity<TypeTest> getTypeTest(@PathVariable("typeTestId") Integer typeTestId){
        return ResponseEntity.ok(typeTestService.getTypeTest(typeTestId));
    }
}

