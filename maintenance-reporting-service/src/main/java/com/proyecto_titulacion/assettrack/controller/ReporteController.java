package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.model.Mantenimiento;
import com.proyecto_titulacion.assettrack.model.Reporte;
import com.proyecto_titulacion.assettrack.model.ReporteCorrectivo;
import com.proyecto_titulacion.assettrack.model.ReportePreventivo;
import com.proyecto_titulacion.assettrack.service.GestorReporteService;
import com.proyecto_titulacion.assettrack.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;
    @Autowired
    private GestorReporteService gestorReporteService;

    @GetMapping
    public ResponseEntity<Page<Reporte>> getAllReporte(int page, int size) {
        try {
            Page<Reporte> reportePage = this.reporteService.getAllReporte(page, size);
            if (reportePage.hasContent()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(reportePage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}/preventivo")
    public ResponseEntity<Boolean> createReportePreventivo(@PathVariable("id") Long ordenTrabajoId, @RequestBody ReportePreventivo reportePreventivo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.gestorReporteService.createReportePreventivo(ordenTrabajoId, reportePreventivo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{id}/correctivo")
    public ResponseEntity<Boolean> createReporteCorrectivo(@PathVariable("id") Long ordenTrabajoId, @RequestBody ReporteCorrectivo reporteCorrectivo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.gestorReporteService.createReporteCorrectivo(ordenTrabajoId, reporteCorrectivo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReporte(@PathVariable("id") Long reporteId) {
        try {
            Reporte reporte = this.reporteService.getReporteById(reporteId);
            if (reporte.getTipoMantenimiento().equals(Mantenimiento.PREVENTIVO)) {
                return ResponseEntity.ok(this.gestorReporteService.getReportePreventivo(reporte));
            } else if (reporte.getTipoMantenimiento().equals(Mantenimiento.CORRECTIVO)) {
                return ResponseEntity.ok(this.gestorReporteService.getReporteCorrectivo(reporte));
            } else {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
