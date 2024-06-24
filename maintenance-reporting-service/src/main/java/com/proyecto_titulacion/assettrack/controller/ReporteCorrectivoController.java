package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.ReporteCorrectivoDTO;
import com.proyecto_titulacion.assettrack.service.ReporteCorrectivoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes/correctivo")
public class ReporteCorrectivoController {

    @Autowired
    private ReporteCorrectivoServiceImpl service;

    @GetMapping
    public List<ReporteCorrectivoDTO> getAllReportes() {
        return service.getAllReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteCorrectivoDTO> getReporteById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getReporteById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReporteCorrectivoDTO> createReporte(@RequestBody ReporteCorrectivoDTO dto) {
        return new ResponseEntity<>(service.createReporte(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteCorrectivoDTO> updateReporte(@PathVariable Long id, @RequestBody ReporteCorrectivoDTO dto) {
        return new ResponseEntity<>(service.updateReporte(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Long id) {
        service.deleteReporte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
