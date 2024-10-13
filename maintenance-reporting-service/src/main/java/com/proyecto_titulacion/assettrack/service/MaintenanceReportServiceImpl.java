package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.entity.MaintenanceReport;
import com.proyecto_titulacion.assettrack.repository.MaintenanceReportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MaintenanceReportServiceImpl implements MaintenanceReportService {
    @Autowired
    private MaintenanceReportRepository maintenanceReportRepository;

    @Override
    public MaintenanceReport getMaintenanceReportById(Long id) {
        return this.maintenanceReportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reporte de Mantenimiento no encontrado con ID: " + id));
    }

    @Override
    public LocalDate getLastMaintenanceDateByAssetId(Long assetId) {
        return this.maintenanceReportRepository.findLastMaintenanceDateByAssetId(assetId).orElse(null);
    }

    @Override
    public Page<MaintenanceReport> getMaintenanceReportsByAssetId(Long assetId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.maintenanceReportRepository.getMaintenanceReportsByAssetId(assetId, pageRequest);
    }

    public MaintenanceReport createMaintenanceReport(MaintenanceReport cleateMaintenanceReport) {
        return this.maintenanceReportRepository.save(cleateMaintenanceReport);
    }

    @Override
    public MaintenanceReport updateMaintenanceReport(Long id, MaintenanceReport updateMaintenanceReport) {
        MaintenanceReport maintenanceReport = this.maintenanceReportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reporte de Mantenimiento no encontrado con ID: " + id));
        updateMaintenanceReport.setId(maintenanceReport.getId());
        return this.maintenanceReportRepository.save(updateMaintenanceReport);
    }
}
