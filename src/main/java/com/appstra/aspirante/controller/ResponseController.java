package com.appstra.aspirante.controller;

import com.appstra.aspirante.entity.Response;
import com.appstra.aspirante.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Response")
public class ResponseController {
    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("/saveresponse")
    @Operation(summary = "Guardar Respuesta", description = "Guardar nueva respuesta")
    public ResponseEntity<Response> saveResponse(@Validated @RequestBody Response response){
        return ResponseEntity.ok(responseService.saveResponse(response));
    }

    @PutMapping("/updateresponse")
    @Operation(summary = "Actualizar Respuesta", description = "Actualizar respuesta existente")
    public ResponseEntity<Response> updateResponse(@Validated @RequestBody Response response){
        return ResponseEntity.ok(responseService.updateResponse(response));
    }

    @DeleteMapping("/deleteresponse/{responseId}")
    @Operation(summary = "Eliminar Respuesta", description = "Eliminar respuesta por ID")
    public ResponseEntity<Boolean> deleteResponse(@PathVariable("responseId") Integer responseId){
        return ResponseEntity.ok(responseService.deleteResponse(responseId));
    }

    @GetMapping("/listresponses")
    @Operation(summary = "Lista de Respuestas", description = "Obtener lista de todas las respuestas")
    public ResponseEntity<List<Response>> listResponses(){
        return ResponseEntity.ok(responseService.listResponses());
    }

    @GetMapping("/{responseId}")
    @Operation(summary = "Información de la Respuesta", description = "Obtener información de la respuesta por ID")
    public ResponseEntity<Response> getResponse(@PathVariable("responseId") Integer responseId){
        return ResponseEntity.ok(responseService.getResponse(responseId));
    }
}

