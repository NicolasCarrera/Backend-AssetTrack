package com.proyecto_titulacion.assettrack.service;

import com.proyecto_titulacion.assettrack.model.entity.MaintenanceReport;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface MaintenanceReportService {
    MaintenanceReport getMaintenanceReportById(Long id);

    LocalDate getLastMaintenanceDateByAssetId(Long assetId);

    Page<MaintenanceReport> getMaintenanceReportsByAssetId(Long assetId, int page, int size);

    MaintenanceReport createMaintenanceReport(MaintenanceReport cleateMaintenanceReport);

    MaintenanceReport updateMaintenanceReport(Long id, MaintenanceReport updateMaintenanceReport);
}
