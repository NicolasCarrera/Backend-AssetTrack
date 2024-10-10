package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.MaintenanceReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MaintenanceReportRepository extends JpaRepository<MaintenanceReport, Long> {
    @Query("SELECT MAX(m.date) FROM MaintenanceReport m WHERE m.assetId = :assetId")
    Optional<LocalDate> findLastMaintenanceDateByAssetId(@Param("assetId") Long assetId);
    @Query("SELECT m FROM MaintenanceReport m WHERE m.assetId = :assetId")
    Page<MaintenanceReport> getMaintenanceReportsByAssetId(@Param("assetId") Long assetId, PageRequest pageRequest);
}