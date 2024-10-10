package com.proyecto_titulacion.assettrack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
@Tag(name = "Test", description = "API para testar los métodos HTTP")
public class TestController {
    @Operation(summary = "Método GET para probar la API", description = "Retorna una respuesta de prueba para el método GET")
    @GetMapping
    public ResponseEntity<String> getMapping() {
        return ResponseEntity.ok("GET");
    }

    @Operation(summary = "Método POST para probar la API", description = "Retorna una respuesta de prueba para el método POST")
    @PostMapping
    public ResponseEntity<String> postMapping(){
        return ResponseEntity.ok("POST");
    }

    @Operation(summary = "Método PUT para probar la API", description = "Retorna una respuesta de prueba para el método PUT")
    @PutMapping
    public ResponseEntity<String> putMapping() {
        return ResponseEntity.ok("PUT");
    }

    @Operation(summary = "Método DELETE para probar la API", description = "Retorna una respuesta de prueba para el método DELETE")
    @DeleteMapping
    public ResponseEntity<String> deleteMapping() {
        return ResponseEntity.ok("DELETE");
    }
}
