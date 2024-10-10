package com.proyecto_titulacion.assettrack.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
@Tag(name = "Test", description = "API para testar los métodos HTTP")
public class TestController {
    @GetMapping
    public ResponseEntity<String> getMapping() {
        return ResponseEntity.ok("GET");
    }
    @PostMapping
    public ResponseEntity<String> postMapping(){
        return ResponseEntity.ok("POST");
    }
    @PutMapping
    public ResponseEntity<String> putMapping() {
        return ResponseEntity.ok("PUT");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteMapping() {
        return ResponseEntity.ok("Delete");
    }
}
