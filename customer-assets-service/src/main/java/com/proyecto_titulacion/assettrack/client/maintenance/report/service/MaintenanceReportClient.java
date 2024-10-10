package com.proyecto_titulacion.assettrack.client.maintenance.report.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "maintenance-reporting-service")
public interface MaintenanceReportClient {
    @GetMapping("/api/v1/maintenance/reports/assets/{assetId}/last-maintenance-date")
    LocalDate getLastMaintenanceDateByAssetId(@RequestParam("assetId") Long assetId);
}
