package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.TestParameters;
import com.appstra.aspirante.service.TestParametersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/TestParameters")
public class TestParametersController {
    private final TestParametersService testParametersService;

    public TestParametersController(TestParametersService testParametersService) {
        this.testParametersService = testParametersService;
    }

    @PostMapping("/savetestparameters")
    @Operation(summary = "Guardar Parámetros de Prueba", description = "Guardar parámetros para la prueba")
    public ResponseEntity<TestParameters> saveTestParameters(@Validated @RequestBody TestParameters testParameters){
        return ResponseEntity.ok(testParametersService.saveTestParameters(testParameters));
    }

    @PutMapping("/updatetestparameters")
    @Operation(summary = "Actualizar Parámetros de Prueba", description = "Actualizar parámetros de prueba existentes")
    public ResponseEntity<TestParameters> updateTestParameters(@Validated @RequestBody TestParameters testParameters){
        return ResponseEntity.ok(testParametersService.updateTestParameters(testParameters));
    }

    @DeleteMapping("/deletetestparameters/{testParametersId}")
    @Operation(summary = "Eliminar Parámetros de Prueba", description = "Eliminar parámetros de prueba por ID")
    public ResponseEntity<Boolean> deleteTestParameters(@PathVariable("testParametersId") Integer testParametersId){
        return ResponseEntity.ok(testParametersService.deleteTestParameters(testParametersId));
    }

    @GetMapping("/listtestparameters")
    @Operation(summary = "Lista de Parámetros de Prueba", description = "Obtener lista de todos los parámetros de prueba")
    public ResponseEntity<List<TestParameters>> listTestParameters(){
        return ResponseEntity.ok(testParametersService.listTestParameters());
    }

    @GetMapping("/{testParametersId}")
    @Operation(summary = "Información de Parámetros de Prueba", description = "Obtener información de los parámetros de prueba por ID")
    public ResponseEntity<TestParameters> getTestParameters(@PathVariable("testParametersId") Integer testParametersId){
        return ResponseEntity.ok(testParametersService.getTestParameters(testParametersId));
    }
}

