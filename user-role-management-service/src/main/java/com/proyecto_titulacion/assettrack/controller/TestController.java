package com.proyecto_titulacion.assettrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
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
