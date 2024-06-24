package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.dto.ReportePreventivoDTO;
import com.proyecto_titulacion.assettrack.service.ReportePreventivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes/preventivo")
public class ReportePreventivoController {

    @Autowired
    private ReportePreventivoService service;

    @GetMapping
    public List<ReportePreventivoDTO> getAllReportes() {
        return service.getAllReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportePreventivoDTO> getReporteById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getReporteById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReportePreventivoDTO> createReporte(@RequestBody ReportePreventivoDTO dto) {
        return new ResponseEntity<>(service.createReporte(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportePreventivoDTO> updateReporte(@PathVariable Long id, @RequestBody ReportePreventivoDTO dto) {
        return new ResponseEntity<>(service.updateReporte(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Long id) {
        service.deleteReporte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
