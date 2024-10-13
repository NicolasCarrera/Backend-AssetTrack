package com.proyecto_titulacion.assettrack.controller;

import com.proyecto_titulacion.assettrack.model.dto.MaintenanceReportDTO;
import com.proyecto_titulacion.assettrack.model.entity.MaintenanceReport;
import com.proyecto_titulacion.assettrack.service.MaintenanceReportService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*")
public class MaintenanceReportController {
    @Autowired
    private MaintenanceReportService maintenanceReportService;

    @GetMapping("/assets/{assetId}")
    public ResponseEntity<Page<MaintenanceReport>> getMaintenanceReportsByAssetId(
            @PathVariable("assetId") Long assetId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Page<MaintenanceReport> maintenanceReports = this.maintenanceReportService.getMaintenanceReportsByAssetId(assetId, page, size);
        return ResponseEntity.ok(maintenanceReports);
    }

    @PostMapping
    public ResponseEntity<MaintenanceReport> createMaintenanceReport(@RequestBody MaintenanceReport cleateMaintenanceReport){
        MaintenanceReport maintenanceReport = this.maintenanceReportService.createMaintenanceReport(cleateMaintenanceReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceReport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceReport> getMaintenanceReportById(@PathVariable("id") Long id) {
        try {
            MaintenanceReport maintenanceReport = this.maintenanceReportService.getMaintenanceReportById(id);
            return ResponseEntity.ok(maintenanceReport);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceReport> updateMaintenanceReport(@PathVariable("id") Long id, @RequestBody MaintenanceReport updateMaintenanceReport){
        MaintenanceReport maintenanceReport = this.maintenanceReportService.updateMaintenanceReport(id, updateMaintenanceReport);
        return ResponseEntity.ok(maintenanceReport);
    }

    @GetMapping("/assets/{assetId}/last-maintenance-date")
    public ResponseEntity<LocalDate> getLastMaintenanceDateByAssetId(@PathVariable("assetId") Long assetId) {
        return ResponseEntity.ok(this.maintenanceReportService.getLastMaintenanceDateByAssetId(assetId));
    }

}
